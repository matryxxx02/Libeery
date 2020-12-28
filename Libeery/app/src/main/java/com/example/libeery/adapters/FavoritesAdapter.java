package com.example.libeery.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libeery.R;
import com.example.libeery.viewModel.ListViewModel;
import com.example.libeery.model.BeerRoom;
import com.google.android.material.snackbar.Snackbar;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private final ListViewModel viewModel;
    private Activity context;

    public FavoritesAdapter(ListViewModel viewModel, Activity context) {
        this.viewModel = viewModel;
        this.context = context;
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

    BeerRoom mRecentlyDeletedItem;
    int mRecentlyDeletedItemPosition;

    public void deleteItem(int position) {
        mRecentlyDeletedItem = viewModel.favoriteList.getValue().get(position);
        mRecentlyDeletedItemPosition = position;
        viewModel.delete(viewModel.favoriteList.getValue().get(position));
        notifyItemRemoved(position);
        showUndoSnackbar();
    }

    private void showUndoSnackbar() {
        View view = context.findViewById(R.id.coordinator_layout);
        Snackbar snackbar = Snackbar.make(view, R.string.snack_bar_text, Snackbar.LENGTH_LONG);
//        snackbar.setAction(R.string.snack_bar_undo, v -> undoDelete());
        snackbar.show();
    }

    //Issue: When undo a deletion, deleted beer does not appear immediately in recycler view. Need to switch fragment and go back to see
    //the beer again. Moreover, if trying to delete this beer once again, the beer will not be deleted and can even be duplicated.
    /*private void undoDelete() {
        viewModel.favoriteList.getValue().add(mRecentlyDeletedItemPosition, mRecentlyDeletedItem);
        notifyItemInserted(mRecentlyDeletedItemPosition);
    }*/

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