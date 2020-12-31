package com.example.libeery.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.libeery.model.BeerRoom;
import com.example.libeery.model.Beers;

import java.util.List;

@Dao
public interface BeerDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BeerRoom beerRoom);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<BeerRoom> beerRoom);

    @Update
    void update(BeerRoom beerRoom);

    @Query("DELETE FROM beer_table")
    void deleteAll();

    @Delete
    void delete(BeerRoom beerRoom);

    @Query("SELECT * FROM beer_table ORDER BY name ASC")
    LiveData<List<BeerRoom>> getAlphabetizedBeers();

    @Query("SELECT * FROM beer_table WHERE favorite = 1 or (favorite is null)")
    LiveData<List<BeerRoom>> getFavoriteBeers();

}
