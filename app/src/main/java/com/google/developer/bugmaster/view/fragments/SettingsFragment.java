package com.google.developer.bugmaster.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.developer.bugmaster.view.activity.MainActivity;
import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.presenters.Presenter;
import com.google.developer.bugmaster.presenters.PresenterInterface;

/**
 * Created by Андрей on 19.09.2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat {

    PresenterInterface presenter;


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        else throw new RuntimeException("ActionBar Device Conflict!");

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) presenter.onBackButtonClick();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    public void setPresenter(PresenterInterface presenter) {
        this.presenter = presenter;
    }
}
