package com.aya.taskdetails.network

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import retrofit2.Response
import java.io.IOException

abstract class SafeAmiRequestLoadingDialogue(var mContext: Context) {
    lateinit var loadingDialoge: LoadingDialogue
    @Throws(ApiException::class)
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T? {
        if (! isConnectedToNetwork(mContext)) {
            Log.e("SafeApiRequest", "no network")
            throw ApiException("no network connection")
        }
        if (mContext is Activity)
            loadingDialoge =
                LoadingDialogue(mContext)
        if (mContext is Activity)
            loadingDialoge.show()
        val response = call.invoke()
        if (response.isSuccessful) {
            if (mContext is Activity)
                loadingDialoge.hide()
            return response.body()!!
        } else {
            Log.e("SafeApiRequest", response.message() + ",url" + response.raw().request.url)
            if (mContext is Activity)
                loadingDialoge.hide()
            throw ApiException(response.code().toString())
        }
    }

    @Throws(ApiException::class)
    suspend fun <T : Any> adiRequestNoDialogue(call: suspend () -> Response<T>): T? {
        if (!isConnectedToNetwork(mContext)) {
            Log.e("SafeApiRequest", "no network")
            throw ApiException("no network connection")
        }
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            Log.e("SafeApiRequest", response.message() + "" + response.raw().request.url)
            throw ApiException(response.code().toString())
        }
    }

    fun isConnectedToNetwork(mContext: Context): Boolean {
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (cm != null) {
            if (Build.VERSION.SDK_INT < 23) {
                val ni = cm.activeNetworkInfo
                if (ni != null) {
                    return ni.isConnected && (ni.type == ConnectivityManager.TYPE_WIFI || ni.type == ConnectivityManager.TYPE_MOBILE)
                }
            } else {
                val n = cm.activeNetwork
                if (n != null) {
                    val nc = cm.getNetworkCapabilities(n)
                    return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc!!.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI
                    )
                }
            }
        }

        return false
    }
}

class ApiException(message: String) : IOException(message)