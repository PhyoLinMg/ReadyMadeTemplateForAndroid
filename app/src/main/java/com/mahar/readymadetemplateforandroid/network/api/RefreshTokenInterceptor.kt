package com.mahar.agent.network.api

import android.content.Context
import android.util.Log
import com.comquas.mizzimaapp.utils.MySharedPreference

import com.google.gson.Gson
import com.mahar.agent.data.response.login.RefreshTokenResponse
import com.mahar.agent.utils.Constant
import okhttp3.*

class RefreshTokenInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val response = chain.proceed(chain.request().newBuilder().build())
            val responseCode = response.code()
            if (responseCode != 401) {
                return response
            }
            response.close()
            val refreshTokenResponse = chain.proceed(getRefreshTokenRequest())
            if (refreshTokenResponse.isSuccessful) {
                val result = refreshTokenResponse.body()?.string()
                result?.let {
                    val data = Gson().fromJson(it, RefreshTokenResponse::class.java)
                    data.refreshToken?.let { token ->
                        MySharedPreference.setString(context, Constant.token, token)
                        val request = chain.request().newBuilder()
                            .removeHeader("Authorization")
                            .addHeader("Authorization", "bearer $token")
                            .build()
                        return chain.proceed(request)
                    }
                }
            }
            throw Exception("Something went wrong")
        } catch (e: Exception) {
            throw e
        }
    }

    private fun getRefreshTokenRequest(): Request {
        val token = MySharedPreference.getString(context, Constant.token)
        return Request.Builder().apply {
            get()
            url("${Constant.ENDPOINT}refresh-token")
            addHeader("Content-Type", "application/json")
            if (token != "") {
                addHeader("Authorization", "bearer $token")
            }
        }.build()
    }

}