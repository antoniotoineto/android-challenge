package com.mc.mimo.moviechallenge.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;


public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView bmImage;

    public LoadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        Bitmap img = null;
        try {
            InputStream in = new java.net.URL(urls[0]).openStream();
            img = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return img;
    }

    protected void onPostExecute(Bitmap img) {
        bmImage.setImageBitmap(img);
    }
}
