package com.google.developer.bugmaster.data;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/**
 * Created by Андрей on 21.09.2017.
 */

public class ImageLoader {

    Activity activity;

    public ImageLoader(Activity activity) {
        this.activity = activity;
    }

    public Bitmap loadImage(String imageAsset){
        Bitmap loadedImage = null;

        AsyncLoader loader = new AsyncLoader(imageAsset);
        loader.execute();

        try {
            loadedImage = loader.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return loadedImage;
    }

    private class AsyncLoader extends AsyncTask<Void, Void, Bitmap>{

        String imageAsset;

        public AsyncLoader(String imageAsset) {
            this.imageAsset = imageAsset;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            Bitmap result = null;

            InputStream input;

            AssetManager manager = activity.getAssets();
            try {
                input = manager.open(imageAsset);
                result = BitmapFactory.decodeStream(input);
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
        }
    }
}
