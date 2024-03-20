package com.example.tugasbeduamobprog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<List<String>> dataList = new MutableLiveData<>();

    public void addData(String data) {
        List<String> currentList = dataList.getValue();
        if (currentList == null) {
            currentList = new ArrayList<>();
        }
        currentList.add(data);
        dataList.setValue(currentList);
    }

    public LiveData<List<String>> getData() {
        return dataList;
    }
}
