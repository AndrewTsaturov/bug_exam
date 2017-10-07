package com.google.developer.bugmaster.base;

/**
 * Created by Shwarz Andrei on 10.09.2017.
 */

public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

}
