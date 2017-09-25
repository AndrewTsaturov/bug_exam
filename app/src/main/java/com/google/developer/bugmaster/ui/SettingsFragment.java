package com.google.developer.bugmaster.ui;

import android.os.Bundle;
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFragmentInterface((MainActivity) getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) fragmentInterface.listScreenLaunch();

        return super.onOptionsItemSelected(item);
    }

    public void setFragmentInterface(MainActivity mainActivity){
        fragmentInterface = mainActivity;
    }
}
