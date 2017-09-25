package com.google.developer.bugmaster.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.developer.bugmaster.MainActivity;
import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.data.ImageLoader;
import com.google.developer.bugmaster.data.Insect;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Андрей on 19.09.2017.
 */

public class InsectDetailsFragment extends Fragment{

    @BindView(R.id.details_img_view) ImageView insectImage;
    @BindView(R.id.details_txt_common_name) TextView commonNameTxtView;
    @BindView(R.id.details_txt_scient_name) TextView scientNammeTxtView;
    @BindView(R.id.details_txt_classification) TextView classificationTextView;
    @BindView(R.id.details_danger_level) ProgressBar dangerRatingView;

    private Unbinder unbinder;

    FragmentInterface fragmentInterface;

    Insect insect;


    public void setInsect(Insect insect) {
        this.insect = insect;
    }

    private void setFragmentInterface(MainActivity mainActivity){
        fragmentInterface = mainActivity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFragmentInterface((MainActivity) getActivity());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_incect_details, container, false);

        unbinder = ButterKnife.bind(this, fragmentView);

        setupView();

        return fragmentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

    private void setupView(){
         commonNameTxtView.setText(insect.getName());
         scientNammeTxtView.setText(insect.getScientificName());
         classificationTextView.setText(getString(R.string.classification) + insect.getClassification());

         dangerRatingView.setProgress(insect.getDangerLevel());

        ImageLoader loader = new ImageLoader(getActivity());

        insectImage.setImageBitmap(loader.loadImage(insect.getImageAsset()));
    }

}
