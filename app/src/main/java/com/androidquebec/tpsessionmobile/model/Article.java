package com.androidquebec.tpsessionmobile.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

public class Article implements Comparable<Article> {


    private long id;
    private String titre;
    private String description;
    private double prix;
    private int rating;

    private LocalDateTime date;
    private String image;


    public Article() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", rating=" + rating +
                ", date=" + date +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public int compareTo(Article o) {
        return this.id > o.id ? 1 : -1;
    }

}