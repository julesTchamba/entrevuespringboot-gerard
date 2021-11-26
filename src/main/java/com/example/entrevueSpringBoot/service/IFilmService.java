package com.example.entrevueSpringBoot.service;

import com.example.entrevueSpringBoot.exception.FilmResourceException;
import com.example.entrevueSpringBoot.model.Film;
import java.util.Optional;

public interface IFilmService {
    Optional<Film> trouveViaId(Long id) throws FilmResourceException;
    Film enregistrerOuMettreAJourFilm(Film film) throws FilmResourceException;
}
