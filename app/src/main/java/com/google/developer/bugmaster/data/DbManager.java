package com.google.developer.bugmaster.data;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Singleton that controls access to the SQLiteDatabase instance
 * for this application.
 * Handling the threads with AsyncTask loader
 */
public class DbManager {

    private BugsDbHelper dbHelper;
    private DbLoader loader;

    public DbManager(Context context) {

        dbHelper = new BugsDbHelper(context);
        loader = new DbLoader();
    }

    public ArrayList<Insect> loadInsects(){
        ArrayList<Insect> result = new ArrayList<>();

        loader.execute();

        try {
            result = loader.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }

    private class DbLoader extends AsyncTask<Void, Void, ArrayList<Insect>>{

        ArrayList<Insect> insects = new ArrayList<>();

        @Override
        protected ArrayList<Insect> doInBackground(Void... params) {
            dbHelper.fillDatabase();

            insects = dbHelper.getInsectsList();

            return insects;
        }

        @Override
        protected void onPostExecute(ArrayList<Insect> insects) {
            super.onPostExecute(insects);
        }
    }

}
