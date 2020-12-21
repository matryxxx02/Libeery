package com.example.libeery.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libeery.R;
import com.example.libeery.adapters.SearchBeerAdapter;
import com.example.libeery.model.Beer;
import com.example.libeery.viewModel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchBeerFragment extends Fragment {

    public static final String TEXT_IN_SEARCHBAR = "TextSearchView";
    public static final String LIST_TEST = "listTest";

    private RecyclerView recyclerView;
    private SearchView beerSearchView;
    private SearchBeerAdapter adapter;
    private ListViewModel viewModel;
    private List<Beer> beers = new ArrayList<>();
    private String textSearchView = "";

    public SearchBeerFragment() {}

    public static SearchBeerFragment newInstance() {
        return new SearchBeerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_beer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);

        initRecyclerView();
        observeData();
        if(savedInstanceState==null){
            viewModel.getBeers();
        }
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewSearch);
        beerSearchView = (SearchView) getView().findViewById(R.id.searchBeerText);
        RecyclerView.LayoutManager layoutManager;

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            layoutManager = new GridLayoutManager(getActivity(),2 );
        else
            layoutManager = new LinearLayoutManager(getActivity());

        adapter = new SearchBeerAdapter(viewModel, beers);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(true);

        beerSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                textSearchView = newText;
                adapter.filter(newText);
                return true;
            }
        });
    }

    private void observeData() {
        viewModel.getBeerList().observe(getViewLifecycleOwner(), list -> {
            List<Beer> beers = list.getBeers();

            this.beers.addAll(beers);
            adapter.notifyDataSetChanged();

            getView().findViewById(R.id.progressBar).setVisibility(View.GONE);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null){
            beerSearchView.setQuery(textSearchView, false);

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save list state

        outState.putString(TEXT_IN_SEARCHBAR, textSearchView);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

}
