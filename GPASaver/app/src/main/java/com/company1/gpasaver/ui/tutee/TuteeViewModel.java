package com.company1.gpasaver.ui.tutee;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TuteeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TuteeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is a Tutee fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
