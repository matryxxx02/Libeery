package com.example.libeery.view;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.libeery.R;
import com.example.libeery.model.Beer;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.palette.graphics.Palette;

import java.util.Calendar;
import java.util.Date;

public class DetailsBeerView extends AppCompatActivity {
    private Beer beer;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_details);

        initView();
        Intent i = getIntent();
        setupActionBar();
        showTitleOnlyOnCollapsed();

        if(i != null){
            beer = i.getParcelableExtra("beer");
            System.out.println(beer.toString());

            if(beer.getLabels() != null && beer.getLabels().getMedium() != null){
                Picasso.get().load(beer.getLabels().getLarge()).into(LoadImage());
            }
            //TODO: gerer le else (si il y a pas d'image mettre une image de beer)

            beerName.setText(beer.getNameDisplay());
            description.setText(beer.getStyle().getDescription());
            if(beer.getAbv()!=null) abv.setText(beer.getAbv()+" %");
            String date = "/";
            if(beer.getStyle().getUpdateDate() != null) date = beer.getStyle().getUpdateDate().split(" ")[0].replace("-","/");
            updateDate.setText(date);
            infoStatus.setText((beer.getStatus()=="verified")?"Verified":"Not verified");
            if(beer.getDescription() != null) miniDescript.setText(beer.getDescription().length()>0 ? beer.getDescription() : "/");
            else miniDescript.setText(beer.getName());

        }

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
                    titleToolbar.setTitle(beer.getNameDisplay());
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
//        titleToolbar.setExpandedTitleColor(getResources().getColor(R.color.colorBlack));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
        }
    }

    void setupColorActionBarIcon(Drawable favoriteItemColor) {
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if ((titleToolbar.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(titleToolbar))) {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorBackground), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorBackground),
                        PorterDuff.Mode.SRC_ATOP);

            } else {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorBackground), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorBackground),
                        PorterDuff.Mode.SRC_ATOP);
            }
        });
    }

    public void initView() {
        beerImage = findViewById(R.id.detailBeerImage);
        titleToolbar = findViewById(R.id.collapsing_toolbar);
        toolbar = findViewById(R.id.toolbar);
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
        Drawable favoriteItemColor = getDrawable(R.drawable.ic_like);
        setupColorActionBarIcon(favoriteItemColor);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
