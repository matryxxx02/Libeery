package com.example.libeery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libeery.R;
import com.example.libeery.data.Beer;
import com.example.libeery.model.ListViewModel;
import com.example.libeery.room.BeerRoom;

import java.util.ArrayList;
import java.util.List;

public class SearchBeerAdapter extends RecyclerView.Adapter<SearchBeerAdapter.ViewHolder> {

        private List<Beer> beers = new ArrayList<>();
        private ListViewModel viewModel;

        public SearchBeerAdapter(List<Beer> beers, ListViewModel viewModel) {
            this.beers = beers;
            this.viewModel = viewModel;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.searchbeer_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SearchBeerAdapter.ViewHolder holder, int position) {
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
                    BeerRoom beerRoom = new BeerRoom(beer.getId(), beer.getName(), beer.getCatName(), beer.getCountry(), beer.getDescription());
                    if(beer.isFavorite())
                        viewModel.insert(beerRoom);
                    else
                        viewModel.delete(beerRoom);
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