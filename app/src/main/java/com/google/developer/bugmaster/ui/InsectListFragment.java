package com.google.developer.bugmaster.ui;

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

import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.presenters.Presenter;
import com.google.developer.bugmaster.presenters.PresenterInterface;
import com.google.developer.bugmaster.views.InsectRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Андрей on 19.09.2017.
 */

public class InsectListFragment extends Fragment {

    private Unbinder unbinder;

    PresenterInterface presenter;

    @BindView(R.id.insect_list) RecyclerView insectListView;

    @BindView(R.id.list_fab) FloatingActionButton quizLaunchFab;

    public InsectListFragment() {
        presenter = new Presenter();
    }

    //native Fragment callbacks -->

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                presenter.onSortMenuItemClick();
                break;
            case R.id.action_settings:
                presenter.onSettingsMenuItemClick();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupView(){
        InsectRecyclerAdapter recyclerAdapter = new InsectRecyclerAdapter();

        insectListView.setLayoutManager(new LinearLayoutManager(getContext()));
        insectListView.setAdapter(recyclerAdapter);


        quizLaunchFab.setOnClickListener(v -> presenter.onQuizFabClick());
    }

    //insect list position getter



}
