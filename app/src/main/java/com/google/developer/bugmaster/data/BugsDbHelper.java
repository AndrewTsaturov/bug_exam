package com.google.developer.bugmaster.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.developer.bugmaster.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Database helper class to facilitate creating and updating
 * the database from the chosen schema.
 * This class contains methods to filling the SQLite Database
 */
public class BugsDbHelper extends SQLiteOpenHelper {

    private static final String TAG = BugsDbHelper.class.getSimpleName();


    //Used to read data from res/ and assets/
    private Resources mResources;


    //private JSON Consts
    private String JSON_ARRAY_NAME = "insects";
    private String JSON_KEY_NAME = "friendlyName";
    private String JSON_KEY_SCIENTIFIC_NAME = "scientificName";
    private String JSON_KEY_CLASSIFICATION = "classification";
    private String JSON_KEY_IMAGE_ASSET = "imageAsset";
    private String JSON_KEY_DANGER_LEVEL = "dangerLevel";


    public BugsDbHelper(Context context) {
        super(context, SQLiteConsts.DATABASE_NAME, null, SQLiteConsts.DB_VERSION);

        mResources = context.getResources();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLiteConsts.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQLiteConsts.UPDATE);
        onCreate(db);
    }

    public void fillDatabase() {
        String rawJson = getJsonStringFromResoures();
        JSONArray jsonArray = getArrayFromRawJson(rawJson);

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                addInsectToDatabase(createInsectFromJsonObj(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Insect> getInsectsList() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SQLiteConsts.GET_TABLE_FOR_CURSOR, null);

        ArrayList result = new ArrayList();

        while (cursor.moveToNext()) {
            Insect insect = new Insect();
            insect.setId(cursor.getInt(0));
            insect.setName(cursor.getString(1));
            insect.setScientificName(cursor.getString(2));
            insect.setClassification(cursor.getString(3));
            insect.setImageAsset(cursor.getString(4));
            insect.setDangerLevel(cursor.getInt(5));

            result.add(insect);
        }
        return result;
    }

    private void addInsectToDatabase(Insect insect) {
        SQLiteDatabase db = getWritableDatabase();

        if (!isInsectExist(db, insect)) {
            ContentValues cv = new ContentValues();
            cv.put(SQLiteConsts.NAME_ID, insect.getName());
            cv.put(SQLiteConsts.SCIENTIFIC_NAME_ID, insect.getScientificName());
            cv.put(SQLiteConsts.CLASSIFICATION_ID, insect.getClassification());
            cv.put(SQLiteConsts.IMAGE_ASSET_ID, insect.getImageAsset());
            cv.put(SQLiteConsts.DANGER_LEVEL_ID, insect.getDangerLevel());

            db.insert(SQLiteConsts.TABLE_NAME, null, cv);
            db.close();
        }
    }


    private boolean isInsectExist(SQLiteDatabase db, Insect insect) {
        boolean result = false;

        Cursor cursor = db.rawQuery(SQLiteConsts.GET_TABLE_FOR_CURSOR, null);

        while (cursor.moveToNext())
            if (cursor.getString(2).equals(insect.getScientificName())) result = true;

        return result;
    }

    private Insect createInsectFromJsonObj(JSONObject object) {
        Insect result = new Insect();

        try {
            result.setName(object.getString(JSON_KEY_NAME));
            result.setScientificName(object.getString(JSON_KEY_SCIENTIFIC_NAME));
            result.setClassification(object.getString(JSON_KEY_CLASSIFICATION));
            result.setImageAsset(object.getString(JSON_KEY_IMAGE_ASSET));
            result.setDangerLevel(object.getInt(JSON_KEY_DANGER_LEVEL));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }


    private JSONArray getArrayFromRawJson(String rawJson) {
        JSONArray result = null;

        try {
            JSONObject raw = new JSONObject(rawJson);
            result = raw.getJSONArray(JSON_ARRAY_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String getJsonStringFromResoures() {
        StringBuilder builder = new StringBuilder();

        InputStream in = mResources.openRawResource(R.raw.insects);

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;

        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Parse resource into key/values
        final String rawJson = builder.toString();

        return rawJson;
    }
}
