package com.example.libeery.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.libeery.api.BeerApi;
import com.example.libeery.api.BeerClient;
import com.example.libeery.model.Beer;
import com.example.libeery.model.BeerRoom;
import com.example.libeery.db.BeerDAO;
import com.example.libeery.db.BeerRoomDatabase;
import com.example.libeery.model.Beers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerRepository {

    private BeerDAO beerDao;
    private LiveData<List<BeerRoom>> listBeersRoom;
    private BeerApi beerApi;



    public BeerRepository(Application application) {
        BeerRoomDatabase db = BeerRoomDatabase.getDatabase(application);
        beerDao = db.beerDAO();
        listBeersRoom = beerDao.getAlphabetizedBeers();
        beerApi = BeerClient.createService(BeerApi.class);
    }

    public LiveData<List<BeerRoom>> getListBeersRoom() {
        return listBeersRoom;
    }

    public void insert(BeerRoom word) {
        BeerRoomDatabase.databaseWriteExecutor.execute(() -> {
            beerDao.insert(word);
        });
    }

    public void delete(BeerRoom beer) {
        BeerRoomDatabase.databaseWriteExecutor.execute(() -> {
            beerDao.delete(beer);
        });
    }

    public Call<Beers> getBeers(){
        return beerApi.getBeers();
    }
}
