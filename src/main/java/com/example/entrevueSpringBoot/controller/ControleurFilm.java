package com.example.entrevueSpringBoot.controller;

import com.example.entrevueSpringBoot.model.Film;
import com.example.entrevueSpringBoot.service.IFilmService;
import com.example.entrevueSpringBoot.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@CrossOrigin(origins = Const.ORIGINS, maxAge = 3600)
@RestController
@RequestMapping("/api/*")
public class ControleurFilm {

    @Autowired
    private IFilmService filmService;

    @GetMapping(value = "/film/{id}")
    public ResponseEntity<Film> recupererLeFilm(@PathVariable(value = "id") Long id) {
        Optional<Film> filmTrouve = filmService.trouveViaId(id);
        return new ResponseEntity<Film>(filmTrouve.get(), HttpStatus.FOUND);
    }

    @PostMapping(value = "/film")
    @Transactional
    public ResponseEntity<Film> enregistrerLeFilm(@RequestBody Film film) {
        Film userSaved = filmService.enregistrerOuMettreAJourFilm(film);
        return new ResponseEntity<Film>(userSaved, HttpStatus.CREATED);
    }
}
