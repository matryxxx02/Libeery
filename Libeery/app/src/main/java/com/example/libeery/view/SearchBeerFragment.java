package com.example.libeery.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libeery.R;
import com.example.libeery.adapter.SearchBeerAdapter;
import com.example.libeery.api.BeerApi;
import com.example.libeery.api.BeerClient;
import com.example.libeery.data.Beers;
import com.example.libeery.data.DataGenerator;
import com.example.libeery.model.ListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchBeerFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView searchBeerTextView;
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
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchBeerTextView = (TextView) getView().findViewById(R.id.searchBeerText);
        viewModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
        adapter = new SearchBeerAdapter(DataGenerator.getInstance().getData(), viewModel);
        recyclerView.setAdapter(adapter);

        searchBeerTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter = new SearchBeerAdapter(DataGenerator.getInstance().getData(searchBeerTextView.getText().toString()), viewModel);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //TEST APIII
        //TODO: recup les datas du body et les parse en Beer
        Call<Beers> mealsCall = BeerClient.getBeerClient().create(BeerApi.class).getBeers();
        mealsCall.enqueue(new Callback<Beers>() {
            @Override
            public void onResponse(@NonNull Call<Beers> call, @NonNull Response<Beers> response) {
                List<Beers.Beer> b = response.body().getBeers();
                System.out.println(b.get(1).toString());

            }

            @Override
            public void onFailure(@NonNull Call<Beers> call, @NonNull Throwable t) {

            }
        });
    }
}
