package com.jaycee.jaynetwork.manager.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.jaycee.jaynetwork.manager.constants.ConnectivityType;

import static com.jaycee.jaynetwork.manager.constants.ConnectivityType.MOBILE;
import static com.jaycee.jaynetwork.manager.constants.ConnectivityType.NONE;
import static com.jaycee.jaynetwork.manager.constants.ConnectivityType.WIFI;

public class NetworkUtil {

    private static final String TAG = "NetworkUtil";

    private static ConnectivityManager connectivityManager;
    private static NetworkInfo activeNetwork;

    /*Checks if it is connected to the network*/
    public static boolean isConnectedToNetwork(Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean isConnected = false;
        if (connectivityManager != null) {
            activeNetwork = connectivityManager.getActiveNetworkInfo();
            isConnected = (activeNetwork != null) && (activeNetwork.isConnectedOrConnecting());
        }
        return isConnected;
    }

    /*Check the type of network status*/
    private static ConnectivityType getConnectivityStatus(Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return MOBILE;
        }
        return NONE;
    }

    /*Check the type of network involved*/
    public static ConnectivityType getConnectionType(Context context) {
        ConnectivityType conn = NetworkUtil.getConnectivityStatus(context);
        ConnectivityType status = null;

        switch (conn) {
            case WIFI:
                status = WIFI;
                break;
            case MOBILE:
                status = MOBILE;
                break;
            case NONE:
                status = NONE;
                break;
            default:
                Log.e(TAG, "getConnectionType: error getting type");
        }
        return status;
    }

    /*Detects if the network is fast enough*/
    public static boolean isConnectedFast() {
        activeNetwork = connectivityManager.getActiveNetworkInfo();
        return (activeNetwork != null && activeNetwork.isConnected() && isConnectionFast(activeNetwork.getType(), activeNetwork.getSubtype()));
    }

    /*Detects if the connection is fast*/
    private static boolean isConnectionFast(int type, int subType) {
        if (type == ConnectivityManager.TYPE_WIFI) {
            return true;
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            switch (subType) {
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return false; // ~ 60-100 Kbps
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return false; // ~ 14-64 kbps
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return false; // ~ 50-100 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return true; // ~ 400-1000 kbps
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return true; // ~ 600 - 1400 kbps
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return true; // ~ 100 kbps
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return true; // ~ 2 - 14 mbps
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return true; // ~ 700 - 1700 mbps
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return true; // ~ 1 - 23 mbps
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return true; // ~ 400 - 7000 mbps

                /*Above Api level 7, make sure to set the android:targetSdkVersion
                 * to appropriate level to use this*/
                case TelephonyManager.NETWORK_TYPE_EHRPD: // API Level 11
                    return true; // ~ 1 - 2 mbps
                case TelephonyManager.NETWORK_TYPE_EVDO_B: // API Level 9
                    return true; // ~ 5 mbps
                case TelephonyManager.NETWORK_TYPE_HSPAP:// API Level 13
                    return true; // ~ 10 - 20 mbps
                case TelephonyManager.NETWORK_TYPE_IDEN: // API Level 8
                    return true; // ~ 25 Kbps
                case TelephonyManager.NETWORK_TYPE_LTE: // API Level 11
                    return true; // ~ 10+ mbps

                // UNKNOWN
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                default:
                    return true;
            }
        } else {
            return false;
        }
    }
}
