package com.example.entrevueSpringBoot.repository;

import com.example.entrevueSpringBoot.model.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IActeurRepository extends JpaRepository<Acteur, Long> {
    Optional<Acteur> findById(Long id);
}
