package com.example.libeery.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libeery.R;
import com.example.libeery.view.DetailsBeerView;
import com.example.libeery.viewModel.BeersViewModel;
import com.example.libeery.model.BeerRoom;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchBeerAdapter extends RecyclerView.Adapter<SearchBeerAdapter.ViewHolder> implements Filterable {

    private List<BeerRoom> beers;
    private List<BeerRoom> beerListFull;
    private final BeersViewModel viewModel;

    public SearchBeerAdapter(BeersViewModel viewModel, List<BeerRoom> beers) {
        this.viewModel = viewModel;
        this.beers = beers;
        this.beerListFull = new ArrayList<>(beers);
    }

    public void updateBeers(List<BeerRoom> b) {
        beerListFull = new ArrayList<>(b);
        beers = b;
        notifyDataSetChanged();
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
        return beers ==null?0: beers.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<BeerRoom> filteredList = new ArrayList<>();
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredList.addAll(beerListFull);
                } else {
                    for (BeerRoom b : beerListFull) {
                        if(b.getName().toLowerCase().trim().contains(charString)) {
                            filteredList.add(b);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                beers.clear();
                beers.addAll((List) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private BeerRoom beer;
        public TextView nameTextView;
        public TextView catNameTextView;
        public TextView abvTextView;
        public ImageView favoriteImage;
        public ImageView beerImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nameTextView = itemView.findViewById(R.id.nameTextView);
            this.catNameTextView = itemView.findViewById(R.id.styleNameTextView);
            this.abvTextView = itemView.findViewById(R.id.abvTextView);
            this.favoriteImage = itemView.findViewById(R.id.favoriteImage);
            this.beerImage = itemView.findViewById(R.id.beerImage);
            this.favoriteImage.setOnClickListener(v -> {
                beer.setFavorite(beer.getFavorite()!=1?1:0);
                display(beer);
                if(beer.getFavorite()==1)
                    viewModel.insert(beer);
                else
                    viewModel.delete(beer);
            });

           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailsBeerView.class);
                    intent.putExtra("beer", beer);
                    context.startActivity(intent);
                }
            });

        }

        public void display(BeerRoom beer) {
            this.beer = beer;
            System.out.println(beer.getStyleName());
            nameTextView.setText(beer.getName());
            catNameTextView.setText(beer.getStyleName());
            abvTextView.setText(beer.getAbv());
            if(beer.getImageURL() != null && !beer.getImageURL().isEmpty()){
                Picasso.get().load(beer.getImageURL()).into(this.beerImage);
            } else {
               this.beerImage.setImageResource(R.drawable.ic_beer);
            }
            if(beer.getFavorite()==1)
                favoriteImage.setImageResource(R.drawable.ic_lover);
            else
                favoriteImage.setImageResource(R.drawable.ic_like);
        }
    }
}