package com.jaycee.jaynetwork;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jaycee.jaynetwork.manager.constants.ConnectivityType;
import com.jaycee.jaynetwork.manager.manager.NetworkManagerActivity;

public class MainActivity extends NetworkManagerActivity {

    private static final String TAG = "MainActivity";

    private TextView type;
    private TextView isConnected;
    private TextView isConnectionFast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        type = findViewById(R.id.type);
        isConnected = findViewById(R.id.isConnected);
        isConnectionFast = findViewById(R.id.isConnectionFast);

    }


    @Override
    public void isConnectionFast(boolean isFast) {
        super.isConnectionFast(isFast);
        isConnectionFast.setText("isConnectionFast: " + isFast);
    }

    @Override
    public void isConnected(boolean status) {
        super.isConnected(status);
        isConnected.setText("isConnected: " + status);
    }

    @Override
    public void networkType(ConnectivityType type) {
        super.networkType(type);
        setNetworkType(type);
    }


    public void startMonitoring(View view) {
        // start observing for network changes
        observeNetwork();
    }

    public void stopMonitoring(View view) {
        // stop observing for network changes
        unObserveNetwork();
    }

    // checks for what network is connected
    private void setNetworkType(ConnectivityType connectivityType) {
        switch (connectivityType) {
            case WIFI:
                type.setText("NetworkType: WIFI");
                break;
            case MOBILE:
                type.setText("NetworkType: MOBILE");
                break;
            case NONE:
                type.setText("NetworkType: NONE");
                break;
            default:
                Log.e(TAG, "setNetworkType: Unknown");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unObserveNetwork();
    }
}