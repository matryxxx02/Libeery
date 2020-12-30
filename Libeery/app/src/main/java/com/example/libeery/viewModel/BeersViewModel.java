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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeersViewModel extends ViewModel {

    private BeerRepository beerRepository;
    private MutableLiveData<Beers> beerList = new MutableLiveData<>();
    public final LiveData<List<BeerRoom>> favoriteList;

    public BeersViewModel(BeerRepository br) {
        beerRepository = br;
        favoriteList = beerRepository.getListBeersRoom();
    }

    public MutableLiveData<Beers> getBeerList() {
        return beerList;
    }

    public void getBeers() {

        beerRepository.getBeers().enqueue(new Callback<Beers>() {
            @Override
            public void onResponse(Call<Beers> call, Response<Beers> response) {
                if (response.isSuccessful()) {
                    beerList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Beers> call, Throwable t) {
                beerList.setValue(null);
            }
        });
    }

    public void insert(BeerRoom beerRoom) {
        beerRepository.insert(beerRoom);
    }

    public void delete(BeerRoom beerRoom) {
        beerRepository.delete(beerRoom);
    }
}
