package com.google.developer.bugmaster.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.developer.bugmaster.MainActivity;
import com.google.developer.bugmaster.R;

/**
 * Created by Андрей on 19.09.2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
