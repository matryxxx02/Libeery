package com.example.libeery.view;

import android.os.Bundle;
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
import com.example.libeery.model.ListViewModel;

import java.util.List;

public class FavoritesFragment extends Fragment {

    public static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

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
                catNameTextView.setText(beer.getCatName());
                countryTextView.setText(beer.getCountry());
                favoriteImage.setImageResource(0);
            }
        }
    }

    private RecyclerView recyclerView;
    private ListViewModel viewModel;
    private RecyclerAdapter adapter;

    public FavoritesFragment() {}

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewFavorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewModel = new ViewModelProvider(requireActivity()).get(ListViewModel.class);
        viewModel.favoriteList.observe(getViewLifecycleOwner(), list -> {
            adapter = new FavoritesFragment.RecyclerAdapter(list);
            recyclerView.setAdapter(adapter);
        });
    }
}
