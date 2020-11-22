package com.company1.gpasaver.ui.reply;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReplyViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ReplyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Reply");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
