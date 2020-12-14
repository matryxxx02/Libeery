package com.example.libeery.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libeery.R;
import com.example.libeery.model.Beer;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private final List<Beer> beers;

    public FavoritesAdapter(List<Beer> beers) {
        this.beers = beers;
    }

    @NonNull
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beer_item, parent, false);
        return new FavoritesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.ViewHolder holder, int position) {
        holder.display(beers.get(position));
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

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
        }

        public void display(Beer beer) {
            nameTextView.setText(beer.getName());
            catNameTextView.setText(beer.getNameDisplay());
            countryTextView.setText(beer.getStyle().getShortName());
            favoriteImage.setImageResource(0);
        }
    }
}
