package com.ghassen.education.dao.entities;

import com.ghassen.education.enums.Genre;

/**
 * Created by ghassen.mellassi on 11/17/2021
 */


public class Personne {

    private int id;
    private String nom;
    private Genre genre;

    public Personne(int id,String nom, Genre genre) {
        this.id = id;
        this.nom = nom;
        this.genre = genre;
    }

    public Personne(String nom, Genre genre) {
        this.nom = nom;
        this.genre = genre;
    }

    public Personne() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
