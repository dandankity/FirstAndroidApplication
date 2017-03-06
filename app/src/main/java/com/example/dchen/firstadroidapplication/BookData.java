package com.example.dchen.firstadroidapplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dchen on 3/4/17.
 */

public class BookData {
    private static final String COUNT = "count";
    private static final String START = "start";
    private static final String TOTAL = "total";
    private static final String BOOKS = "books";

    private static JSONObject jsonObject;

    public BookData(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public static int getCOUNT() {
        return jsonObject.optInt(COUNT);
    }

    public static int getSTART() {
        return jsonObject.optInt(START);
    }

    public static int getTOTAL() {
        return jsonObject.optInt(TOTAL);
    }

    public static int getBOOKS() {
        return jsonObject.optInt(BOOKS);
    }

    private String title;
    private String image;
    private String author;
    private String publisher;
    private String publishDate;
    private String summary;
    private double rate;

    public List<Book> getBooks() {
        JSONArray array = jsonObject.optJSONArray(BOOKS);
        List<Book> books = new ArrayList<>(array.length());

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = (JSONObject) array.opt(i);
            books.add(new Book(
                    object.optString("title"),
                    object.optString("image"),
                    object.optJSONArray("author").toString(),
                    object.optString("publisher"),
                    object.optString("pubdate"),
                    object.optString("summary"),
                    object.optJSONObject("rating").optDouble("average")
            ));
        }
        return books;
    }

    public static BookData from(JSONObject jsonObject) {
        return new BookData(jsonObject);
    }

    @Override
    public String toString() {
        return jsonObject.toString();
    }
}
