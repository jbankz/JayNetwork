package com.jaycee.jaynetwork.manager.manager

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jaycee.jaynetwork.manager.constants.ConnectivityType
import com.jaycee.jaynetwork.manager.listener.NetworkListener
import com.jaycee.jaynetwork.manager.monitor.NetworkChangeMonitor

open class NetworkManagerActivity : AppCompatActivity(), NetworkListener {
    private var networkChangeMonitor: NetworkChangeMonitor? = null
    private var intent: IntentFilter? = null
    private var registerState = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkChangeMonitor = NetworkChangeMonitor(this)
        intent = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
    }

    // start observing for network
    protected fun observeNetwork() {
        registerState = 1
        registerReceiver(networkChangeMonitor, intent)
    }

    // observing for network
    protected fun unObserveNetwork() {
        if (registerState == 1) {
            unregisterReceiver(networkChangeMonitor)
        }
    }

    override fun isConnected(status: Boolean) { // do nothing
    }

    override fun networkType(connectivityType: ConnectivityType?) {

    }

    override fun isConnectionFast(isFast: Boolean) { // do nothing
    }
}