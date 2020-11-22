package com.company1.gpasaver.ui.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccountViewModel extends ViewModel{
    private MutableLiveData<String> mText;

    public AccountViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Account");
    }

    // LiveData<> Is "Observed" in the fragment.
    public LiveData<String> getText() {
        return mText;
    }
}
