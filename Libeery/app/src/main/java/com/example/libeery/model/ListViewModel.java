package com.example.libeery.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ListViewModel extends ViewModel {

    public final MutableLiveData<List<Beer>> favoriteList = new MutableLiveData<List<Beer>>();

    public void updateFavoriteList(List<Beer> list) {
        favoriteList.setValue(list);
    }
}
