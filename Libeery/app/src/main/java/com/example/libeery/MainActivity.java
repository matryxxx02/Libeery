package com.example.libeery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    private ChipNavigationBar navBar;
    private Fragment currentFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navBar = findViewById(R.id.navBar);
        currentFragment=new SearchBeerFragment();
        navBar.setItemSelected(R.id.beers, true);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, currentFragment)
                .commit();


        navBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch(id){
                    case R.id.beers:
                        fragment = new SearchBeerFragment();
                        break;
                    case R.id.favorites:
                        fragment = new FavoritesFragment();
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                }

                if(fragment != null ){
                    currentFragment = fragment;
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                } else {
                    System.out.println("Error in creating fragment");
                }
            }
        });

    }
}