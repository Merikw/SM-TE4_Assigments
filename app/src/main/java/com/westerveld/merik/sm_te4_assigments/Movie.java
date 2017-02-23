package com.westerveld.merik.sm_te4_assigments;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Merik on 16/02/2017.
 */

public class Movie {

    private String title;
    private int year;
    private String rated;
    private String runTime;
    private String genre;
    private String director;
    private String writer;
    private String plot;
    private double rating;
    private String picURL;
    private Bitmap img;

    public Movie(){

    }

    public Movie(String title, int year, String rated, String runTime, String genre, String director, String writer, String plot, double rating, String picURL){
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.runTime = runTime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.plot = plot;
        this.rating = rating;
        this.picURL = picURL;
    }

    public Movie(String title, int year, String picURL){
        this.title = title;
        this.year = year;
        this.picURL = picURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicURL(){
        return this.picURL;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setImg(Bitmap img){
        this.img = img;
    }

    public Bitmap getImg(){
        return this.img;
    }
}
