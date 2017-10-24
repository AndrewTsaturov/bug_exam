package com.google.developer.bugmaster.view.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.presenters.Presenter;


/**
 * RecyclerView adapter extended with project-specific required methods.
 */
//TODO implement on MVP pattern
public class InsectRecyclerAdapter extends
        RecyclerView.Adapter<InsectListViewHolder> {


    @Override
    public InsectListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.insect_list_item, parent, false);


        return new InsectListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InsectListViewHolder holder, int position) {
      new Presenter.ListPresenter().onBindInsectListViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        int count = new Presenter.ListPresenter().getInsectsListCount();

        return count;
    }

}
