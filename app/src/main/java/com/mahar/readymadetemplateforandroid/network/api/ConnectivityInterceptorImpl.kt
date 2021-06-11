package com.elemental.atantat.network

import android.content.Context
import com.mahar.readymadetemplateforandroid.utils.MyNetworkUty.isNetworkConnected
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityInterceptorImpl @Inject constructor (@ApplicationContext private val context: Context): ConnectivityInterceptor {
    private val appContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isNetworkConnected(appContext))
            throw NoConnectivityException()
        return chain.proceed(chain.request())
    }

}