package com.example.libeery.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.libeery.repository.BeerRepository;

public class BeersViewModelFactory implements ViewModelProvider.Factory {

    private static BeerRepository beerRepository;
    private  BeersViewModelFactory() {}
    private static BeersViewModelFactory INSTANCE = new BeersViewModelFactory();

    public static BeersViewModelFactory getInstance() {
        return INSTANCE;
    }

    public static void initFactory(Application app) {
        if(beerRepository == null)
            beerRepository = new BeerRepository(app);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(BeersViewModel.class)){
            return (T) new BeersViewModel(beerRepository);
        } else {
            throw new IllegalArgumentException("Unknown Class");
        }
    }
}
