package com.jaycee.jaynetwork.manager.monitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.jaycee.jaynetwork.manager.listener.NetworkListener
import com.jaycee.jaynetwork.manager.utility.NetworkUtil.getConnectionType
import com.jaycee.jaynetwork.manager.utility.NetworkUtil.isConnectedFast
import com.jaycee.jaynetwork.manager.utility.NetworkUtil.isConnectedToNetwork

class NetworkChangeMonitor(private val networkListener: NetworkListener) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        networkListener.isConnected(isConnectedToNetwork(context))
        networkListener.networkType(getConnectionType(context))
        networkListener.isConnectionFast(isConnectedFast)
    }

}