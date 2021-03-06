package com.example.dchen.firstadroidapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;

/**
 * Created by dchen on 3/4/17.
 */

public class ImageLoader {

    public static final Bitmap loadImage(String url) {
        Bitmap bm = null;
        InputStream is= null;
        BufferedInputStream bis = null;

        try {
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is, 8192);
            bm = BitmapFactory.decodeStream(bis);


        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(bis != null){
                try {
                    bis.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return bm;
    }
}
