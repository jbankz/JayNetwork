package com.jaycee.jaynetwork.manager.utility

import android.content.Context
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import com.jaycee.jaynetwork.manager.constants.ConnectivityType
import android.net.NetworkInfo as NetworkInfo1

object NetworkUtil {
    private var connectivityManager: ConnectivityManager? = null
    private var activeNetwork: NetworkInfo1? = null
    /*Checks if it is connected to the network*/
    @JvmStatic
    fun isConnectedToNetwork(context: Context): Boolean {
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isConnected = false
        if (connectivityManager != null) {
            activeNetwork = connectivityManager!!.activeNetworkInfo
            isConnected = activeNetwork != null && activeNetwork!!.isConnectedOrConnecting
        }
        return isConnected
    }

    /*Check the type of network status*/
    private fun getConnectivityStatus(context: Context): ConnectivityType {
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        assert(connectivityManager != null)
        activeNetwork = connectivityManager!!.activeNetworkInfo
        if (null != activeNetwork) {
            if (activeNetwork!!.type == ConnectivityManager.TYPE_WIFI) return ConnectivityType.WIFI
            if (activeNetwork!!.type == ConnectivityManager.TYPE_MOBILE) return ConnectivityType.MOBILE
        }
        return ConnectivityType.NONE
    }

    /*Check the type of network involved*/
    @JvmStatic
    fun getConnectionType(context: Context): ConnectivityType? {
        val conn = getConnectivityStatus(context)
        var status: ConnectivityType? = null
        when (conn) {
            ConnectivityType.WIFI -> status = ConnectivityType.WIFI
            ConnectivityType.MOBILE -> status = ConnectivityType.MOBILE
            ConnectivityType.NONE -> status = ConnectivityType.NONE
        }
        return status
    }

    /*Detects if the network is fast enough*/
    val isConnectedFast: Boolean
        get() {
            activeNetwork = connectivityManager!!.activeNetworkInfo
            return activeNetwork != null && activeNetwork!!.isConnected && isConnectionFast(activeNetwork!!.type, activeNetwork!!.subtype)
        }

    /*Detects if the connection is fast*/
    private fun isConnectionFast(type: Int, subType: Int): Boolean {
        return when (type) {
            ConnectivityManager.TYPE_WIFI -> {
                true
            }
            ConnectivityManager.TYPE_MOBILE -> {
                when (subType) {
                    TelephonyManager.NETWORK_TYPE_1xRTT -> false // ~ 60-100 Kbps
                    TelephonyManager.NETWORK_TYPE_CDMA -> false // ~ 14-64 kbps
                    TelephonyManager.NETWORK_TYPE_EDGE -> false // ~ 50-100 kbps
                    TelephonyManager.NETWORK_TYPE_EVDO_0 -> true // ~ 400-1000 kbps
                    TelephonyManager.NETWORK_TYPE_EVDO_A -> true // ~ 600 - 1400 kbps
                    TelephonyManager.NETWORK_TYPE_GPRS -> true // ~ 100 kbps
                    TelephonyManager.NETWORK_TYPE_HSDPA -> true // ~ 2 - 14 mbps
                    TelephonyManager.NETWORK_TYPE_HSPA -> true // ~ 700 - 1700 mbps
                    TelephonyManager.NETWORK_TYPE_HSUPA -> true // ~ 1 - 23 mbps
                    TelephonyManager.NETWORK_TYPE_UMTS -> true // ~ 400 - 7000 mbps
                    TelephonyManager.NETWORK_TYPE_EHRPD -> true // ~ 1 - 2 mbps
                    TelephonyManager.NETWORK_TYPE_EVDO_B -> true // ~ 5 mbps
                    TelephonyManager.NETWORK_TYPE_HSPAP -> true // ~ 10 - 20 mbps
                    TelephonyManager.NETWORK_TYPE_IDEN -> true // ~ 25 Kbps
                    TelephonyManager.NETWORK_TYPE_LTE -> true // ~ 10+ mbps
                    TelephonyManager.NETWORK_TYPE_UNKNOWN -> true
                    else -> true
                }
            }
            else -> {
                false
            }
        }
    }
}