package com.company1.gpasaver.ui.requests;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
//Not used rn
public class RequestsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RequestsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}