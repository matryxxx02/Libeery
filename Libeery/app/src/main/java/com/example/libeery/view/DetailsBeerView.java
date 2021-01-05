package com.example.libeery.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libeery.R;
import com.example.libeery.model.Beer;
import com.example.libeery.model.BeerRoom;
import com.example.libeery.viewModel.BeersViewModel;
import com.example.libeery.viewModel.BeersViewModelFactory;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProvider;

import static com.example.libeery.R.drawable.ic_like_detail;
import static com.example.libeery.R.drawable.ic_lover_detail;

public class DetailsBeerView extends AppCompatActivity {
    private BeerRoom beer;
    private ImageView beerImage;
    private CollapsingToolbarLayout titleToolbar;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private TextView beerName;
    private TextView description;
    private TextView abv;
    private TextView updateDate;
    private TextView infoStatus;
    private TextView miniDescript;
    private ProgressBar progressBarDetail;
    private BeersViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_details);

        BeersViewModelFactory factory = BeersViewModelFactory.getInstance();
        viewModel = new ViewModelProvider(this, factory).get(BeersViewModel.class);

        initView();
        Intent i = getIntent();
        setupActionBar();
        showTitleOnlyOnCollapsed();

        if(i != null){
            beer = i.getParcelableExtra("beer");
            setBeerFields();
        }

    }

    private void setBeerFields() {
        if(beer.getImageURL() != null && !beer.getImageURL().isEmpty()){
            Picasso.get().load(beer.getImageURL()).into(LoadImage());
        } else {
            //TODO: gerer le else (si il y a pas d'image mettre une image de beer)
            this.beerImage.setImageResource(R.drawable.ic_beer);
            findViewById(R.id.srimImage).setVisibility(View.INVISIBLE);
            progressBarDetail.setVisibility(View.INVISIBLE);
        }

        beerName.setText(beer.getCatName());
        if(beer.getLongDescription() != null) description.setText(beer.getLongDescription());

        if(beer.getAbv()!=null) abv.setText(beer.getAbv());

        if(beer.getUpdateDate() != null) updateDate.setText(beer.getUpdateDate().split(" ")[0].replace("-","/"));

        if(beer.getDescription() != null) miniDescript.setText(beer.getDescription().length()>0 ? beer.getDescription() : "/");
        else miniDescript.setText(beer.getName());
    }

    private void showTitleOnlyOnCollapsed() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    titleToolbar.setTitle(beer.getCatName());
                    isShow = true;
                } else if(isShow) {
                    titleToolbar.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }

    private Target LoadImage(){
        return new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                beerImage.setImageBitmap(bitmap);
                beerImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                progressBarDetail.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                progressBarDetail.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPrepareLoad(Drawable arg0) {
                progressBarDetail.setVisibility(View.VISIBLE);
            }
        };
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        titleToolbar.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        titleToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.colorBackground));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    void setupColorActionBarIcon(Drawable favoriteItemColor) {
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if ((titleToolbar.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(titleToolbar))) {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorBackground), PorterDuff.Mode.SRC_ATOP);

            } else {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorBackground), PorterDuff.Mode.SRC_ATOP);
            }
        });
    }

    public void initView() {
        beerImage = findViewById(R.id.detailBeerImage);
        titleToolbar = findViewById(R.id.collapsing_toolbar);
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_detail);
        appBarLayout = findViewById(R.id.appbar);
        description = findViewById(R.id.description);
        beerName = findViewById(R.id.beerName);
        abv = findViewById(R.id.abv);
        updateDate = findViewById(R.id.updateDate);
        infoStatus = findViewById(R.id.infoStatus);
        miniDescript = findViewById(R.id.miniDescript);
        progressBarDetail = findViewById(R.id.progressBarDetail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        Drawable favoriteItemColor = getDrawable(R.drawable.ic_like_detail);
        setupColorActionBarIcon(favoriteItemColor);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem favoriteItem = menu.findItem(R.id.favoriteDetail);
        if(beer != null && beer.getFavorite()==1)
            favoriteItem.setIcon(getDrawable(R.drawable.ic_lover_detail));
        else
            favoriteItem.setIcon(getDrawable(R.drawable.ic_like_detail));
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favoriteDetail :
                if(beer != null && beer.getFavorite()==1){
                    item.setIcon(ic_like_detail);
                    beer.setFavorite(0);
                    viewModel.delete(beer);
                } else {
                    item.setIcon(ic_lover_detail);
                    beer.setFavorite(1);
                    viewModel.insert(beer);
                }
                return true;
            case android.R.id.home :
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
