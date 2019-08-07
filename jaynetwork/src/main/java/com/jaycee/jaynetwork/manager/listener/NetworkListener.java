package com.jaycee.jaynetwork.manager.listener;

import com.jaycee.jaynetwork.manager.constants.ConnectivityType;

public interface NetworkListener {
    void isConnected(boolean status);

    void networkType(ConnectivityType connectivityType);

    void isConnectionFast(boolean isFast);
}
