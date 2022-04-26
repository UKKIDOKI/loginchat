package com.doit.login.ui.Chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;


    public DashboardViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("채팅방 목록");
    }

    public LiveData<String> getText() {
        return mText;
    }
}