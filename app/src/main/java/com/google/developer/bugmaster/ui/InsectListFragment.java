package com.google.developer.bugmaster.ui;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.developer.bugmaster.AppBugMaster;
import com.google.developer.bugmaster.MainActivity;
import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.data.InsectRecyclerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Андрей on 19.09.2017.
 */

public class InsectListFragment extends Fragment implements OnItemInterface {

    private Unbinder unbinder;

    private ArrayList<Insect> listOfInsects;

    private FragmentInterface fragmentInterface;

    private InsectRecyclerAdapter recyclerAdapter;

    @BindView(R.id.insect_list) RecyclerView insectListView;

    @BindView(R.id.list_fab) FloatingActionButton quizLaunchFab;

    //native Fragment callbacks -->

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFragmentInterface((MainActivity) getActivity());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_insect_list, container, false);

        unbinder = ButterKnife.bind(this, fragmentView);

        setupView();

        return fragmentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //menu callbacks -->

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sort:
                fragmentInterface.sortInsectList();
                break;
            case R.id.action_settings:
                fragmentInterface.settingsScreenLaunch();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onInsectClick(int position) {
        AppBugMaster.insectListChoosenPosition = position;

        fragmentInterface.detailsScreenLaunch(position);
    }

    //Setup views -->

    private void setupView(){
        recyclerAdapter = new InsectRecyclerAdapter(listOfInsects, getContext());
        recyclerAdapter.setOnItemInterface(this);

        insectListView.setLayoutManager(new LinearLayoutManager(getContext()));
        insectListView.setAdapter(recyclerAdapter);


        quizLaunchFab.setOnClickListener(v -> fragmentInterface.quizScreenLaunch());
    }

    // public setters -->

    public void setListOfInsects(ArrayList<Insect> listOfInsects) {
        this.listOfInsects = listOfInsects;
    }

    public void setFragmentInterface(MainActivity mainActivity){
        fragmentInterface = mainActivity;
    }

    //insect list position getter



}
