package com.justinjose.wifigames.others

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

class NetworkObserver(context: Context) {
    private val validNetworkConnections: ArrayList<Network> = ArrayList()
    private var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private lateinit var connectivityManagerCallback: ConnectivityManager.NetworkCallback
    private var internetStatusListener: InternetStatusListener? = null
    private var internetStatus: InternetStatus = InternetStatus.Invalid

    companion object {
        fun getInstance(context: Context): NetworkObserver {
            return NetworkObserver(context)
        }
    }

    fun registerListener(internetStatusListener: InternetStatusListener) {
        this.internetStatusListener = internetStatusListener
        connectivityManagerCallback = getConnectivityManagerCallback()
        val networkRequest =
            NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()
        connectivityManager.registerNetworkCallback(networkRequest, connectivityManagerCallback)
        connectivityManager.activeNetworkInfo ?: announceStatusIfUnique()
    }

    fun unregisterListener() {
        this.internetStatusListener = null
        connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
    }

    private fun announceStatusIfUnique() {
        if (validNetworkConnections.isNotEmpty()) {
            if (internetStatus == InternetStatus.Available) {
                return
            }
            internetStatus = InternetStatus.Available
            internetStatusListener?.available()
            return
        }
        if (internetStatus == InternetStatus.Unavailable) {
            return
        }
        internetStatus = InternetStatus.Unavailable
        internetStatusListener?.unavailable()
    }

    private fun getConnectivityManagerCallback() = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            val networkCapability = connectivityManager.getNetworkCapabilities(network)
            val hasNetworkConnection =
                networkCapability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    ?: false
            if (!hasNetworkConnection) {
                announceStatusIfUnique()
                return
            }
            addNetwork(network)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            removeNetwork(network)
        }

        override fun onCapabilitiesChanged(
            network: Network, networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                addNetwork(network)
                return
            }
            removeNetwork(network)
        }
    }

    private fun addNetwork(network: Network) {
        if (validNetworkConnections.contains(network)) {
            return
        }
        validNetworkConnections.add(network)
        announceStatusIfUnique()
    }

    private fun removeNetwork(network: Network) {
        if (!validNetworkConnections.contains(network)) {
            return
        }
        validNetworkConnections.remove(network)
        announceStatusIfUnique()
    }
}

interface InternetStatusListener {
    fun available()
    fun unavailable()
}

sealed class InternetStatus {
    object Available : InternetStatus()
    object Unavailable : InternetStatus()
    object Invalid : InternetStatus()
}