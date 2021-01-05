package com.example.libeery.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libeery.R;
import com.example.libeery.viewModel.BeersViewModel;
import com.example.libeery.model.BeerRoom;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private final BeersViewModel viewModel;
    private List<BeerRoom> favoriteList;

    public FavoritesAdapter(BeersViewModel viewModel, List<BeerRoom> list) {
        this.viewModel = viewModel;
        this.favoriteList = list;
    }

    public void updateFavorite(List<BeerRoom> list) {
        favoriteList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favoritebeer_item, parent, false);
        return new FavoritesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.ViewHolder holder, int position) {
        holder.display(favoriteList.get(position));
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public void deleteItem(int position) {
        favoriteList.get(position).setFavorite(0);
        viewModel.delete(favoriteList.get(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView catNameTextView;
        public TextView countryTextView;
        public TextView descriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nameTextView = itemView.findViewById(R.id.nameTextView);
            this.catNameTextView = itemView.findViewById(R.id.catNameTextView);
            this.countryTextView = itemView.findViewById(R.id.abvTextView);
            this.descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }

        public void display(BeerRoom beer) {
            nameTextView.setText(beer.getName());
            catNameTextView.setText(beer.getStyleName());
            countryTextView.setText(beer.getAbv());
            descriptionTextView.setText(beer.getDescription());
        }
    }
}