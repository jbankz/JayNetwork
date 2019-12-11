package com.jaycee.jaynetwork

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.jaycee.jaynetwork.manager.constants.ConnectivityType
import com.jaycee.jaynetwork.manager.manager.NetworkManagerFragment

/**
 * A placeholder fragment containing a simple view.
 */
class MainFragmentFragment : NetworkManagerFragment() {

    private var type: TextView? = null
    private var isConnected: TextView? = null
    private var isConnectionFast: TextView? = null
    private var startObserving: Button? = null
    private var stopObserving: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        type = view.findViewById(R.id.type)
        isConnected = view.findViewById(R.id.isConnected)
        isConnectionFast = view.findViewById(R.id.isConnectionFast)
        startObserving = view.findViewById(R.id.startObserving)
        stopObserving = view.findViewById(R.id.stopObserving)

        startObserving!!.setOnClickListener {
            observeNetwork()
        }

        stopObserving!!.setOnClickListener {
            unObserveNetwork()
        }

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
        private const val TAG = "MainFragmentFragment"
    }

}
