package com.google.developer.bugmaster.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.presenters.Presenter;
import com.google.developer.bugmaster.view.custom.DangerLevelView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Андрей on 17.10.2017.
 */
//TODO implement onclick listener
public class InsectListViewHolder extends RecyclerView.ViewHolder implements RecyclerViewInterface {


    @BindView(R.id.item_danger_level) DangerLevelView dangerLevelView;
    @BindView(R.id.item_txt_common_name) TextView commonNameTextViev;
    @BindView(R.id.item_txt_scient_name) TextView scientNameTextView;

    public InsectListViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(v -> new Presenter.ListPresenter().onInsectListItemClick(getAdapterPosition()));
    }

    @Override
    public void setDangerLevel(int dangerLevel) {
        dangerLevelView.setDangerLevel(dangerLevel);
    }

    @Override
    public void setCommonName(String commonName) {
       commonNameTextViev.setText(commonName);
    }

    @Override
    public void setScientificName(String scientificName) {
       scientNameTextView.setText(scientificName);
    }
}
