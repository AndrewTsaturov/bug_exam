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

    FragmentInterface fragmentInterface;

    ActionBar actionBar;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFragmentInterface((MainActivity) getActivity());
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        actionBar.setDisplayHomeAsUpEnabled(true);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) fragmentInterface.listScreenLaunch();

        return super.onOptionsItemSelected(item);
    }

    //Setter

    public void setFragmentInterface(MainActivity mainActivity){
        fragmentInterface = mainActivity;
    }

    public void setActionBar(ActionBar actionBar) {
        this.actionBar = actionBar;
    }
}
