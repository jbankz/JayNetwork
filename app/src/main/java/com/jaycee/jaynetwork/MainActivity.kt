package com.jaycee.jaynetwork

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.jaycee.jaynetwork.manager.constants.ConnectivityType
import com.jaycee.jaynetwork.manager.manager.NetworkManagerActivity

class MainActivity : NetworkManagerActivity() {
    private var type: TextView? = null
    private var isConnected: TextView? = null
    private var isConnectionFast: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        type = findViewById(R.id.type)
        isConnected = findViewById(R.id.isConnected)
        isConnectionFast = findViewById(R.id.isConnectionFast)
    }

    override fun isConnectionFast(isFast: Boolean) {
        super.isConnectionFast(isFast)
        isConnectionFast!!.text = "isConnectionFast: $isFast"
    }

    override fun isConnected(status: Boolean) {
        super.isConnected(status)
        isConnected!!.text = "isConnected: $status"
    }

    override fun networkType(type: ConnectivityType?) {
        super.networkType(type)
        setNetworkType(type)
    }

    fun startMonitoring(view: View?) { // start observing for network changes
        observeNetwork()
    }

    fun stopMonitoring(view: View?) { // stop observing for network changes
        unObserveNetwork()
    }

    // checks for what network is connected
    private fun setNetworkType(connectivityType: ConnectivityType?) {
        when (connectivityType) {
            ConnectivityType.WIFI -> type!!.text = "NetworkType: WIFI"
            ConnectivityType.MOBILE -> type!!.text = "NetworkType: MOBILE"
            ConnectivityType.NONE -> type!!.text = "NetworkType: NONE"
            else -> Log.e(TAG, "setNetworkType: Unknown")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unObserveNetwork()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}