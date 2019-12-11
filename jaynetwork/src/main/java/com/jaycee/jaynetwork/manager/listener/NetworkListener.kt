package com.jaycee.jaynetwork.manager.listener

import com.jaycee.jaynetwork.manager.constants.ConnectivityType

interface NetworkListener {
    fun isConnected(status: Boolean)
    fun networkType(connectivityType: ConnectivityType?)
    fun isConnectionFast(isFast: Boolean)
}