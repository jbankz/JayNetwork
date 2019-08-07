package com.jaycee.jaynetwork.manager.manager;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jaycee.jaynetwork.manager.constants.ConnectivityType;
import com.jaycee.jaynetwork.manager.listener.NetworkListener;
import com.jaycee.jaynetwork.manager.monitor.NetworkChangeMonitor;

public class NetworkManagerActivity extends AppCompatActivity implements NetworkListener {

    private NetworkChangeMonitor networkChangeMonitor;
    private IntentFilter intent;
    private int registerState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkChangeMonitor = new NetworkChangeMonitor(this);
        intent = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    }

    // start observing for network
    protected void observeNetwork() {
        registerState = 1;
        registerReceiver(networkChangeMonitor, intent);
    }

    // observing for network
    protected void unObserveNetwork() {
        if (registerState == 1) {
            unregisterReceiver(networkChangeMonitor);
        }
    }

    @Override
    public void networkType(ConnectivityType connectivityType) {
        // do nothing
    }

    @Override
    public void isConnected(boolean status) {
        // do nothing
    }

    @Override
    public void isConnectionFast(boolean isFast) {
        // do nothing
    }
}
