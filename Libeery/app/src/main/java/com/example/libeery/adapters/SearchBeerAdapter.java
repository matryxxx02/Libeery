package com.example.libeery.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libeery.R;
import com.example.libeery.model.Beer;
import com.example.libeery.viewModel.ListViewModel;
import com.example.libeery.model.BeerRoom;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchBeerAdapter extends RecyclerView.Adapter<SearchBeerAdapter.ViewHolder> {

    private final List<Beer> beers;
    private List<Beer> filteredBeers;
    private final ListViewModel viewModel;

    public SearchBeerAdapter(ListViewModel viewModel, List<Beer> beers) {
        this.viewModel = viewModel;
        this.beers = beers;
        this.filteredBeers = beers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchbeer_item, parent, false);
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

    public void filter(String text) {
        filteredBeers.clear();
        if(text.isEmpty()){
            filteredBeers.addAll(viewModel.getBeerList().getValue().getBeers());
        } else{
            text = text.toLowerCase();
            for(Beer beer: viewModel.getBeerList().getValue().getBeers()){
                if(beer.getName().toLowerCase().contains(text))
                    filteredBeers.add(beer);
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private Beer beer;
        public TextView nameTextView;
        public TextView catNameTextView;
        public TextView countryTextView;
        public ImageView favoriteImage;
        public ImageView beerImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nameTextView = itemView.findViewById(R.id.nameTextView);
            this.catNameTextView = itemView.findViewById(R.id.catNameTextView);
            this.countryTextView = itemView.findViewById(R.id.countryTextView);
            this.favoriteImage = itemView.findViewById(R.id.favoriteImage);
            this.beerImage = itemView.findViewById(R.id.beerImage);
            this.favoriteImage.setOnClickListener(v -> {
                beer.setFavorite(!beer.isFavorite());
                display(beer);
                BeerRoom beerRoom = new BeerRoom(beer.getId(), beer.getName(), beer.getNameDisplay(), beer.getName(), beer.getDescription());
                if(beer.isFavorite())
                    viewModel.insert(beerRoom);
                else
                    viewModel.delete(beerRoom);
            });
        }

        public void display(Beer beer) {
            this.beer = beer;
            nameTextView.setText(beer.getName());
            catNameTextView.setText(beer.getNameDisplay());
            if(beer.getStyle() != null)
                countryTextView.setText(beer.getStyle().getShortName());
            if(beer.getLabels() != null && beer.getLabels().getMedium() != null)
                Picasso.get().load(beer.getLabels().getMedium()).into(this.beerImage);
            if(beer.isFavorite())
                favoriteImage.setImageResource(R.drawable.ic_lover);
            else
                favoriteImage.setImageResource(R.drawable.ic_like);
        }
    }
}