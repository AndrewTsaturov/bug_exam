package com.google.developer.bugmaster.data;

/**
 * Created by Андрей on 19.09.2017.
 */

public interface SQLiteConsts {
    int DB_VERSION = 1;
    String DATABASE_NAME = "BUGS_DB";
    String TABLE_NAME = "insects";
    String KEY_ID = "id";
    String NAME_ID = "name";
    String SCIENTIFIC_NAME_ID = "scientificName";
    String CLASSIFICATION_ID = "classification";
    String IMAGE_ASSET_ID = "imageAsset";
    String DANGER_LEVEL_ID = "dangerLevel";
    String CREATE_TABLE = "CREATE TABLE insects (id INTEGER PRIMARY KEY, name TEXT, scientificName TEXT, classification TEXT, imageAsset TEXT, dangerLevel INTEGER)";
    String UPDATE = "DROP TABLE IF EXISTS insects";
    String GET_TABLE_FOR_CURSOR = "SELECT * FROM insects";
}
