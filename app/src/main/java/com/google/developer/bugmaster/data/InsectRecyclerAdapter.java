package com.google.developer.bugmaster.data;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.ui.InsectListFragment;
import com.google.developer.bugmaster.ui.OnItemInterface;
import com.google.developer.bugmaster.views.DangerLevelView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView adapter extended with project-specific required methods.
 */

public class InsectRecyclerAdapter extends
        RecyclerView.Adapter<InsectRecyclerAdapter.InsectHolder> {

    private ArrayList<Insect> insects = new ArrayList<>();

    private Context context;

    private OnItemInterface onItemInterface;

    public InsectRecyclerAdapter(ArrayList<Insect> insects, Context context) {
        this.insects = insects;
        this.context = context;
    }

    public void setOnItemInterface(InsectListFragment insectListFragment) {
        onItemInterface = insectListFragment;
    }

    @Override
    public InsectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //FIXME--> refact list_item
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.insect_list_item, parent, false);

        return new InsectHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InsectHolder holder, int position) {
        holder.commonNameTextViev.setText(insects.get(position).getName());
        holder.scientNameTextView.setText(insects.get(position).getScientificName());
        holder.dangerLevel.setDangerLevel(insects.get(position).getDangerLevel(), context);
    }

    @Override
    public int getItemCount() {
        return insects.size();
    }

    /* ViewHolder for each insect item */
    public class InsectHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_danger_level) DangerLevelView dangerLevel;
        @BindView(R.id.item_txt_common_name) TextView commonNameTextViev;
        @BindView(R.id.item_txt_scient_name) TextView scientNameTextView;

        public InsectHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> onItemInterface.onInsectClick(getAdapterPosition()));
        }

    }
}
