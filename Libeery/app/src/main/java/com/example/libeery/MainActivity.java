package com.example.libeery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;

import com.example.libeery.view.FavoritesFragment;
import com.example.libeery.view.SearchBeerFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    private final static String FRAGMENT_STORED_KEY = "Fragment_Stored";

    private ChipNavigationBar navBar;
    private Fragment currentFragment;
    private SparseArray<Fragment> fragmentArray;
    private FrameLayout noInternetFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentArray = new SparseArray<>(2);
        navBar = findViewById(R.id.navBar);

        if(savedInstanceState != null){
            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT_STORED_KEY);
        }else{
            currentFragment = new SearchBeerFragment();
            navBar.setItemSelected(R.id.beers, true);
        }
        replaceFragment(currentFragment);

        navBar.setOnItemSelectedListener(id -> {
            switch (id) {
                case R.id.beers:
                    if (fragmentArray.get(0) == null) {
                        currentFragment = SearchBeerFragment.newInstance();
                        fragmentArray.append(0, currentFragment);
                    } else
                            currentFragment = fragmentArray.get(0);
                    CheckNetwork.checkNetworkInfo(this, type -> { //app crashes sometimes
                        if(type){
                            if(currentFragment == null) {
                                if (fragmentArray.get(0) == null) {
                                    currentFragment = SearchBeerFragment.newInstance();
                                    fragmentArray.append(0, currentFragment);
                                } else
                                    currentFragment = fragmentArray.get(0);
                            }
                            updateNoInternetVisibility(View.INVISIBLE);
                            replaceFragment(currentFragment);
                        }else {
                            if(currentFragment != null){
                                getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
                                currentFragment = null;
                            }
                            updateNoInternetVisibility(View.VISIBLE);
                        }
                    });
                    break;
                case R.id.favorites:
                    updateNoInternetVisibility(View.INVISIBLE);
                    if (fragmentArray.get(1) == null) {
                        currentFragment = FavoritesFragment.newInstance();
                        fragmentArray.append(1, currentFragment);
                    } else
                        currentFragment = fragmentArray.get(1);
                    break;
                default:
                    break;
            }
            replaceFragment(currentFragment);
        });

    }

    private void updateNoInternetVisibility(final int visibility){
        runOnUiThread(() -> noInternetFrameLayout.setVisibility(visibility));
    }

    private void replaceFragment(Fragment newFragment) {
        if(newFragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentContainer, newFragment);
            ft.commit();
        }
    }

    @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState) {
        super.onRestoreInstanceState (savedInstanceState);
    }

}