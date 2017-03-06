package com.example.dchen.firstadroidapplication;

import android.text.TextUtils;

/**
 * Created by dchen on 3/4/17.
 */

public class Book {
    private String title;
    private String image;
    private String author;
    private String publisher;
    private String publishDate;
    private String summary;
    private double rate;

    public Book(String title, String image, String author, String publisher, String publishDate, String summary, double rate) {
        this.title = title;
        this.image = image;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.summary = summary;
        this.rate = rate;
    }

    public String getImage() {

        return image;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getSummary() {
        return summary;
    }

    public double getRate() {
        return rate;
    }

    public String getTitle() {
        return title;
    }

    public String getInformation() {
        return TextUtils.join("_", new String[]{this.getAuthor(),this.getPublisher(),this.getPublishDate()});

    }
}
