package com.google.developer.bugmaster.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Андрей on 17.10.2017.
 */

public interface ModelInterface {
    ArrayList<Insect> loadData();

    Question createQuestion();

    Bitmap loadInsectImage(String imageAsset);
}
