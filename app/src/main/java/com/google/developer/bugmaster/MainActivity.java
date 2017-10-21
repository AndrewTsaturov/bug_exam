package com.google.developer.bugmaster;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.google.developer.bugmaster.views.AppView;


public class MainActivity extends AppCompatActivity {

    AppView appView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appView = new AppView(getSupportFragmentManager());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        appView.stopView();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
