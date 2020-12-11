package com.example.libeery.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libeery.R;
import com.example.libeery.model.ListViewModel;
import com.example.libeery.room.BeerRoom;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private final ListViewModel viewModel;

    public FavoritesAdapter(ListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favoritebeer_item, parent, false);
        return new FavoritesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.ViewHolder holder, int position) {
        holder.display(viewModel.favoriteList.getValue().get(position));
    }

    @Override
    public int getItemCount() {
        return viewModel.favoriteList.getValue().size();
    }

    public void deleteItem(int position) {
        viewModel.delete(viewModel.favoriteList.getValue().get(position));
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
            this.countryTextView = itemView.findViewById(R.id.countryTextView);
            this.descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }

        public void display(BeerRoom beer) {
            nameTextView.setText(beer.getName());
            catNameTextView.setText(beer.getCatName());
            countryTextView.setText(beer.getCountry());
            descriptionTextView.setText(beer.getDescription());
        }
    }
}
