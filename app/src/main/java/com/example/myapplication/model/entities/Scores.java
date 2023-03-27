package com.example.myapplication.model.entities;

public class Scores extends BaseEntity {
    private String pseudo;
    private int score;

    public Scores() {
    }

    public Scores(String pseudo, int score) {
        this.pseudo = pseudo;
        this.score = score;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
