package com.google.developer.bugmaster.base;

import android.support.annotation.NonNull;


public interface MvpView {

    boolean isNetworkConnected();

    void onError(@NonNull String message);

    void showMessage(@NonNull String message);
}
