package com.example.dchen.firstadroidapplication;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.util.Log;



/**
 * Created by dchen on 3/4/17.
 */

public class DataLoader {
    private static final String TAG = "DataLoader";


    public static final JSONObject loadData(String urlString) {
        StringBuilder contentBuilder = new StringBuilder();
        String line = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setReadTimeout(150000);
            connection.setConnectTimeout(150000);
            connection.connect();

            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((line = rd.readLine()) != null) {
                contentBuilder.append(line);
            }

            rd.close();
            connection.disconnect();
            return new JSONObject(contentBuilder.toString());
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return null;
    }
}
