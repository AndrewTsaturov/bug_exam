package com.google.developer.bugmaster.base;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @author ${ShwarzAndrei} @created 06.09.2017
 */

public class SimpleTextWatcher implements TextWatcher {

    public interface CallBackAfterTextChanged {
        void afterTextChanged(Editable s);
    }

    private CallBackAfterTextChanged callBackAfterTextChanged;

    public SimpleTextWatcher(CallBackAfterTextChanged callBackAfterTextChanged) {
        this.callBackAfterTextChanged = callBackAfterTextChanged;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        callBackAfterTextChanged.afterTextChanged(s);
    }
}
