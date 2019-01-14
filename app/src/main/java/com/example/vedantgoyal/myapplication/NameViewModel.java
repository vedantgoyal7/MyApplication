package com.example.vedantgoyal.myapplication;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class NameViewModel extends ViewModel {

    // Create a LiveData with a String
    private MutableLiveData<String> mCurrentName;

    public MutableLiveData<String> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<String>();
        }
        mCurrentName.setValue("random name");
        return mCurrentName;
    }

// Rest of the ViewModel...
}
