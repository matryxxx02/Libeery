package com.example.libeery.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.libeery.room.BeerRoom;
import com.example.libeery.room.BeerDAO;
import com.example.libeery.room.BeerRoomDatabase;

import java.util.List;

public class BeerRepository {

    private BeerDAO beerDao;
    private LiveData<List<BeerRoom>> listBeers;

    // Note that in order to unit test the BeerRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    BeerRepository(Application application) {
        BeerRoomDatabase db = BeerRoomDatabase.getDatabase(application);
        beerDao = db.beerDAO();
        listBeers = beerDao.getAlphabetizedBeers();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<BeerRoom>> getListBeers() {
        return listBeers;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(BeerRoom word) {
        BeerRoomDatabase.databaseWriteExecutor.execute(() -> {
            beerDao.insert(word);
        });
    }

    void delete(BeerRoom beer) {
        BeerRoomDatabase.databaseWriteExecutor.execute(() -> {
            beerDao.delete(beer);
        });
    }
}
