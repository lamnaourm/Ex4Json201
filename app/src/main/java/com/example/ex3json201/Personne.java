package com.example.ex3json201;

public class Personne {

    private String nom;
    private String prenom;
    private String genre;
    private int age;

    public Personne(){

    }

    public Personne(String nom, String prenom, String genre, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
