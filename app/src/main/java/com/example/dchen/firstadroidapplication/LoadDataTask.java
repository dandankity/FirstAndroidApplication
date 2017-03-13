package com.example.dchen.firstadroidapplication;

import android.os.AsyncTask;

import static com.example.dchen.firstadroidapplication.BookData.from;
import static com.example.dchen.firstadroidapplication.DataLoader.loadData;

public class LoadDataTask extends AsyncTask<String, Void, BookData> {
    @Override
    protected BookData doInBackground(String... params) {
        return from(loadData(params[0]));
    }
}
