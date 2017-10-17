package com.google.developer.bugmaster.data;

/**
 * Created by Андрей on 19.09.2017.
 */

public interface SQLiteConsts {
    int DB_VERSION = 1;

    String DATABASE_NAME = "BUGS_DB";
    String TABLE_NAME = "insects";
    String PRIMARY_KEY_COLUMN = "id";
    String NAME_COLUMN = "name";
    String SCIENTIFIC_NAME_COLUMN = "scientificName";
    String CLASSIFICATION_COLUMN = "classification";
    String IMAGE_ASSET_COLUMN = "imageAsset";
    String DANGER_LEVEL_COLUMN = "dangerLevel";

    String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + PRIMARY_KEY_COLUMN + " INTEGER PRIMARY KEY, " +
            NAME_COLUMN + " TEXT, " + SCIENTIFIC_NAME_COLUMN + " TEXT, " + CLASSIFICATION_COLUMN + " TEXT, " +
            IMAGE_ASSET_COLUMN + " TEXT, " + DANGER_LEVEL_COLUMN + " INTEGER)";

    String UPDATE = "DROP TABLE IF EXISTS insects";
    String GET_TABLE_FOR_CURSOR = "SELECT * FROM insects";

    int PRIMARY_KEY_COlUMN_INDEX = 0;
    int NAME_COLUMN_INDEX = 1;
    int SCIENTIFIC_NAME_COLUMN_INDEX = 2;
    int CLASSIFICATION_COLUMN_INDEX = 3;
    int IMAGE_ASSET_COLUMN_INDEX = 4;
    int DANGER_LEVEL_COLUMN_INDEX = 5;

    //
    String OLD_CREATE_TABLE = "CREATE TABLE insects (id INTEGER PRIMARY KEY, name TEXT, " +
            "scientificName TEXT, classification TEXT, imageAsset TEXT, dangerLevel INTEGER)";

    //JSON Consts
    String JSON_ARRAY_NAME = "insects";
    String JSON_KEY_NAME = "friendlyName";
    String JSON_KEY_SCIENTIFIC_NAME = "scientificName";
    String JSON_KEY_CLASSIFICATION = "classification";
    String JSON_KEY_IMAGE_ASSET = "imageAsset";
    String JSON_KEY_DANGER_LEVEL = "dangerLevel";
}
