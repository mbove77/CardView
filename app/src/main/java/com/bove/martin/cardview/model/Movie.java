package com.bove.martin.cardview.model;

/**
 * Created by Martín Bove on 14/04/2018.
 * E-mail: mbove77@gmail.com
 */
public class Movie {
    private String name;
    private int poster;

    public Movie(String name, int poster) {
        this.name = name;
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
}
