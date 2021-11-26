package com.example.entrevueSpringBoot.repository;

import com.example.entrevueSpringBoot.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IFilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findById(Long id);
}