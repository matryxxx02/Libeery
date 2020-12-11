package com.example.libeery.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {BeerRoom.class}, version = 1, exportSchema = false)
public abstract class BeerRoomDatabase extends RoomDatabase {

    public abstract BeerDAO beerDAO();

    private static volatile BeerRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BeerRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BeerRoomDatabase.class) {
                if (INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BeerRoomDatabase.class, "beer_database").build();
            }
        }
        return INSTANCE;
    }

}
