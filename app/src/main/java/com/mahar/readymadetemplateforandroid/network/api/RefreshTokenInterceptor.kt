package com.mahar.agent.network.api

import android.content.Context
import android.util.Log
import com.comquas.mizzimaapp.utils.MySharedPreference

import com.google.gson.Gson

import okhttp3.*

class RefreshTokenInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }


}