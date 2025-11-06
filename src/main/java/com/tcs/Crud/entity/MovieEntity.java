package com.tcs.Crud.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Movies")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String MovieName;
    private String ReleaseDate;
    private String Hero;
    
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String Description;


   
    private String Image;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return MovieName;
    }

    public void setMovieName(String movieName) {
        MovieName = movieName;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String getHero() {
        return Hero;
    }

    public void setHero(String hero) {
        Hero = hero;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
