package com.example.libeery.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SearchView;

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
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class SearchBeerFragment extends Fragment {

    public static final String TEXT_IN_SEARCHBAR = "TextSearchView";

    private boolean initDone = false;
    private String textSearchView = "";
    private List<Beer> beers = new ArrayList<>();
    private RecyclerView recyclerView;
    private SearchView beerSearchView;
    private SearchBeerAdapter adapter;
    private ListViewModel viewModel;

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

        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewSearch);
        viewModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
        initRecyclerView();
        observeData();
        viewModel.getBeers();
    }

    private void initRecyclerView() {
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
        ChipNavigationBar navBar = (ChipNavigationBar) getActivity().findViewById(R.id.navBar);
        navBar.setVisibility(View.INVISIBLE);
        viewModel.getBeerList().observe(getViewLifecycleOwner(), list -> {
            List<Beer> beers = list.getBeers();

            this.beers.addAll(beers);
            adapter.notifyDataSetChanged();

            getView().findViewById(R.id.progressBar).setVisibility(View.GONE);
            navBar.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null)
            beerSearchView.setQuery(textSearchView, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_IN_SEARCHBAR, textSearchView);
    }
}
