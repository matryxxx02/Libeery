package com.example.libeery.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.libeery.api.BeerApi;
import com.example.libeery.api.BeerClient;
import com.example.libeery.model.Beer;
import com.example.libeery.model.BeerRoom;
import com.example.libeery.db.BeerDAO;
import com.example.libeery.db.BeerRoomDatabase;
import com.example.libeery.model.Beers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerRepository {

    private BeerDAO beerDao;
    private BeerApi beerApi;
    private LiveData<List<BeerRoom>> listBeersRoom;
    private LiveData<List<BeerRoom>> favoriteBeersRoom;
    private MutableLiveData<Beers> beerListResponse = new MutableLiveData<>();
    private long FRESH_TIMEOUT = TimeUnit.DAYS.toMillis(1);

    public BeerRepository(Application application) {
        BeerRoomDatabase db = BeerRoomDatabase.getDatabase(application);
        beerDao = db.beerDAO();
        beerApi = BeerClient.createService(BeerApi.class);
        favoriteBeersRoom = beerDao.getFavoriteBeers();
        listBeersRoom = beerDao.getAlphabetizedBeers();
        getBeers();
    }

    public LiveData<List<BeerRoom>> getListBeersRoom() {
        return listBeersRoom;
    }

    public LiveData<List<BeerRoom>> getFavoriteBeersRoom() {
        return favoriteBeersRoom;
    }

    public void insert(BeerRoom beer) {
        BeerRoomDatabase.databaseWriteExecutor.execute(() -> {
            beerDao.update(beer);
        });
    }

    public void delete(BeerRoom beer) {
        BeerRoomDatabase.databaseWriteExecutor.execute(() -> {
            beerDao.update(beer);
        });
    }

    public void getBeers(){
        beerApi.getBeers().enqueue(new Callback<Beers>() {
            @Override
            public void onResponse(Call<Beers> call, Response<Beers> response) {
                if (response.isSuccessful()) {
                    beerListResponse.setValue(response.body());
                    BeerAPItoBeerRoom(beerListResponse.getValue());
                    listBeersRoom = beerDao.getAlphabetizedBeers();
                }
            }

            @Override
            public void onFailure(Call<Beers> call, Throwable t) { beerListResponse.setValue(null); }
        });
    }

    public void BeerAPItoBeerRoom(Beers beers) {
        List<BeerRoom> dbBeers = new ArrayList<>();
        for(Beer beer : beers.getBeers()){
          if(beer.getStyle() != null && beer.getStyle().getCategory() != null && beer.getStyle().getCategory().getName() != null)
              System.out.println(beer.getStyle().getCategory().getName());
            dbBeers.add(new BeerRoom(beer));

        }
        BeerRoomDatabase.databaseWriteExecutor.execute(() -> {
            beerDao.insertAll(dbBeers);
        });
    }
}
