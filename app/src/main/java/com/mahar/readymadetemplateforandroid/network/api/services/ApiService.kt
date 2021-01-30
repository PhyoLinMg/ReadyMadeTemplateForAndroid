package com.elemental.templateapplication.remote.network.services

import android.content.Context
import com.comquas.mizzimaapp.utils.MySharedPreference
import com.elemental.atantat.network.ConnectivityInterceptor
import com.mahar.agent.network.api.RefreshTokenInterceptor
import com.mahar.readymadetemplateforandroid.data.User
import com.mahar.readymadetemplateforandroid.utils.Constant
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.net.Proxy
import java.util.*
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor, context: Context
        ): ApiService {

            val requestInterceptor = Interceptor { chain ->
                val token = MySharedPreference.getString(context, Constant.token)
                val request = chain.request()
                    .newBuilder()
                if (token != null)
                    request.addHeader("Authorization", "Bearer $token")

                return@Interceptor chain.proceed(request.build())
            }

            val dispatcher = Dispatcher()
            dispatcher.maxRequests = 1
            val okHttpClient = OkHttpClient.Builder()
                .dispatcher(dispatcher)
                .connectTimeout(1000000, TimeUnit.SECONDS)
                .readTimeout(1000000, TimeUnit.SECONDS).proxy(Proxy.NO_PROXY)
                .addInterceptor(requestInterceptor)
                .addInterceptor(RefreshTokenInterceptor(context))
                .addInterceptor(connectivityInterceptor)


            return Retrofit.Builder()
                .baseUrl(Constant.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(ApiService::class.java)
        }

    }
}