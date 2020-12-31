package com.example.libeery.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
    import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.libeery.model.Beer;
import com.example.libeery.model.BeerRoom;
import com.example.libeery.model.Beers;
import com.example.libeery.repository.BeerRepository;

import java.util.List;

public class BeersViewModel extends ViewModel {

    private BeerRepository beerRepository;
    private LiveData<List<BeerRoom>> beerList;
    public final LiveData<List<BeerRoom>> favoriteList;

    public BeersViewModel(BeerRepository br) {
        beerRepository = br;
        beerList = beerRepository.getListBeersRoom();
        favoriteList = beerRepository.getFavoriteBeersRoom();
    }

    public LiveData<List<BeerRoom>> getBeerList() {
        return beerList;
    }

    public LiveData<List<BeerRoom>> getFavoriteList() {
        return favoriteList;
    }

    public void insert(BeerRoom beerRoom) {
        beerRepository.insert(beerRoom);
    }

    public void delete(BeerRoom beerRoom) {
        beerRepository.delete(beerRoom);
    }
}
