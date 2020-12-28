package com.example.libeery;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

import androidx.annotation.NonNull;

public class CheckNetwork {

    public static void checkNetworkInfo(Context context, final OnConnectionStatusChange onConnectionStatusChange) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (capabilities == null)
            onConnectionStatusChange.onChange(false);
        connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                onConnectionStatusChange.onChange(true);
            }

            @Override
            public void onLost(@NonNull Network network) {
                onConnectionStatusChange.onChange(false);
            }
        });

    }

    public interface OnConnectionStatusChange {
        void onChange(boolean type);
    }

}
