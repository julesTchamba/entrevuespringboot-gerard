package com.example.entrevueSpringBoot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "films")
public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titre;
    private String description;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "filmId")
    private Set<Acteur> acteurs;

    public Film(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public Film() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Acteur> getActeurs() {
        return acteurs;
    }

    public void setActeurs(Set<Acteur> acteurs) {
        this.acteurs = acteurs;
    }
}