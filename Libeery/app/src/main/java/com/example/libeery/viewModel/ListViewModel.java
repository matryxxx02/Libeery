package com.example.libeery.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.libeery.model.BeerRoom;
import com.example.libeery.repository.BeerRepository;

import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private BeerRepository beerRepository;

    public final LiveData<List<BeerRoom>> favoriteList;

    public ListViewModel(@NonNull Application application) {
        super(application);
        beerRepository = new BeerRepository(application);
        favoriteList = beerRepository.getListBeers();
    }

    public void getBeers(){

    }

    public void insert(BeerRoom beerRoom) {
        beerRepository.insert(beerRoom);
    }

    public void delete(BeerRoom beerRoom) {
        beerRepository.delete(beerRoom);
    }
}
