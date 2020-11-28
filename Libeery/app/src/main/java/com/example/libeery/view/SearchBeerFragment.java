package com.example.libeery.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libeery.R;
import com.example.libeery.data.Beer;
import com.example.libeery.data.DataGenerator;
import com.example.libeery.model.ListViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchBeerFragment extends Fragment {

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        private final List<Beer> beers;

        public RecyclerAdapter(List<Beer> beers) {
            this.beers = beers;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.beer_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
            holder.display(beers.get(position));
        }

        @Override
        public int getItemCount() {
            return beers.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private Beer beer;
            public TextView nameTextView;
            public TextView catNameTextView;
            public TextView countryTextView;
            public ImageView favoriteImage;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                this.nameTextView = itemView.findViewById(R.id.nameTextView);
                this.catNameTextView = itemView.findViewById(R.id.catNameTextView);
                this.countryTextView = itemView.findViewById(R.id.countryTextView);
                this.favoriteImage = itemView.findViewById(R.id.favoriteImage);
                this.favoriteImage.setOnClickListener(v -> {
                    beer.setFavorite(!beer.isFavorite());
                    display(beer);
                    if(favoriteList.contains(beer))
                        favoriteList.remove(beer);
                    else
                        favoriteList.add(beer);
                    viewModel.updateFavoriteList(favoriteList);
                });
            }

            public void display(Beer beer) {
                this.beer = beer;
                nameTextView.setText(beer.getName());
                catNameTextView.setText(beer.getCatName());
                countryTextView.setText(beer.getCountry());
                if(beer.isFavorite())
                    favoriteImage.setImageResource(R.drawable.ic_lover);
                else
                    favoriteImage.setImageResource(R.drawable.ic_like);
            }
        }
    }

    private RecyclerView recyclerView;
    private TextView searchBeerTextView;
    private RecyclerAdapter adapter;
    private ListViewModel viewModel;
    private List<Beer> favoriteList;

    public SearchBeerFragment() {
        favoriteList = new ArrayList<>();
    }

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
        adapter = new RecyclerAdapter(DataGenerator.getInstance().getData());
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);

        searchBeerTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter = new RecyclerAdapter(DataGenerator.getInstance().getData(searchBeerTextView.getText().toString()));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
