package com.example.libeery.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.libeery.model.BeerRoom;
import com.example.libeery.db.BeerDAO;
import com.example.libeery.db.BeerRoomDatabase;

import java.util.List;

public class BeerRepository {

    private BeerDAO beerDao;
    private LiveData<List<BeerRoom>> listBeers;

    public BeerRepository(Application application) {
        BeerRoomDatabase db = BeerRoomDatabase.getDatabase(application);
        beerDao = db.beerDAO();
        listBeers = beerDao.getAlphabetizedBeers();
    }

    public LiveData<List<BeerRoom>> getListBeers() {
        return listBeers;
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
}
