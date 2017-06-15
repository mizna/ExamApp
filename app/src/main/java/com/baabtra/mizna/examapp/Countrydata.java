package com.baabtra.mizna.examapp;

/**
 * Created by MIZNA on 15-06-2017.
 */

public class Countrydata {
    private int rank;
    private String country;
    private String population;
    private String imageurl;

    public Countrydata(int rank, String country, String population, String imageurl) {
        this.rank = rank;
        this.country = country;
        this.population = population;
        this.imageurl = imageurl;
    }

    public int getRank() {
        return rank;
    }

    public String getCountry() {
        return country;
    }

    public String getPopulation() {
        return population;
    }

    public String getImageurl() {
        return imageurl;
    }
}
