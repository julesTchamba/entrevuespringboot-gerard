package com.example.entrevueSpringBoot.service;

import com.example.entrevueSpringBoot.exception.FilmResourceException;
import com.example.entrevueSpringBoot.model.Film;

import com.example.entrevueSpringBoot.repository.IFilmRepository;
import com.example.entrevueSpringBoot.util.Const;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Service
public class FilmServiceImpl implements IFilmService {

    private static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);
    private IFilmRepository filmRepository;

    public FilmServiceImpl() {
        super();
    }

    @Autowired
    public FilmServiceImpl(IFilmRepository filmRepository) {
        super();
        this.filmRepository = filmRepository;
    }


    @Override
    @Transactional(readOnly=false)
    public Film enregistrerOuMettreAJourFilm(Film film) throws FilmResourceException{
        try{
            Film result = filmRepository.save(film);
            return  result;
        } catch(DataIntegrityViolationException ex){
            //si jamais on ne veut pas dupliquer les films avec le meme titre
            //sinon merci de retirer l'annotation @Column(unique) dans le model film
            logger.error(Const.MESSAGE_FILM_EXISTANT, ex);
            throw new FilmResourceException(Const.FILM_DUPLIQUE, Const.MESSAGE_FILM_EXISTANT+film.getTitre(), HttpStatus.CONFLICT);

        } catch(Exception ex){
            logger.error(Const.ERREUR_CREATION, ex);
            throw new FilmResourceException(Const.ERREUR_INTERNE, Const.ERREUR_SAUVEGARDE_FILM+film.getTitre(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Optional<Film> trouveViaId(Long id) throws FilmResourceException {
        Optional<Film> filmTrouve = filmRepository.findById(id);
        if (filmTrouve.isPresent() == false) {
            throw new FilmResourceException(Const.FILM_NON_TROUVE, Const.MESSAGE_FILM_NON_TROUVE + id,HttpStatus.NOT_FOUND);
        }
        return filmTrouve;
    }


}
