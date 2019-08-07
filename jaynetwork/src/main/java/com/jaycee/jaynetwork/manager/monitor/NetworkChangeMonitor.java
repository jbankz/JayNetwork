package com.jaycee.jaynetwork.manager.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jaycee.jaynetwork.manager.listener.NetworkListener;
import com.jaycee.jaynetwork.manager.utility.NetworkUtil;

public class NetworkChangeMonitor extends BroadcastReceiver {

    private static NetworkListener networkListener;

    public NetworkChangeMonitor(NetworkListener networkListener) {
        this.networkListener = networkListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        networkListener.isConnected(NetworkUtil.isConnectedToNetwork(context));
        networkListener.networkType(NetworkUtil.getConnectionType(context));
        networkListener.isConnectionFast(NetworkUtil.isConnectedFast());
    }

}
