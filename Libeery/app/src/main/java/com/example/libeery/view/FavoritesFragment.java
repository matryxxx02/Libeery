package com.example.libeery.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libeery.R;
import com.example.libeery.adapters.FavoritesAdapter;
import com.example.libeery.model.ListViewModel;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListViewModel viewModel;
    private FavoritesAdapter adapter;

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
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewFavorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallBack(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        viewModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
        viewModel.favoriteList.observe(getViewLifecycleOwner(), list -> {
            adapter = new FavoritesAdapter(viewModel);
            recyclerView.setAdapter(adapter);
        });
    }
}
