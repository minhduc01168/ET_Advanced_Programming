package com.example.navi_ga.ui.statistical;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class StatisticalViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public StatisticalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is statistic fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}