package com.example.libeery.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libeery.R;
import com.example.libeery.adapters.FavoritesAdapter;
import com.example.libeery.model.BeerRoom;
import com.example.libeery.viewModel.BeersViewModel;
import com.example.libeery.viewModel.BeersViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private BeersViewModel viewModel;
    private FavoritesAdapter adapter;
    private List<BeerRoom> favoriteList =  new ArrayList<>();

    public FavoritesFragment() {}

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BeersViewModelFactory factory = BeersViewModelFactory.getInstance();
        viewModel = new ViewModelProvider(requireActivity(), factory).get(BeersViewModel.class);

        recyclerView = getView().findViewById(R.id.recyclerViewFavorite);
        RecyclerView.LayoutManager layoutManager;

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            layoutManager = new GridLayoutManager(getActivity(),2 );
        else
            layoutManager = new LinearLayoutManager(getActivity());

        adapter = new FavoritesAdapter(viewModel, favoriteList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallBack(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        viewModel.getFavoriteList().observe(getViewLifecycleOwner(), list -> {
            favoriteList = list;
            adapter.updateFavorite(favoriteList);
        });
    }
}
