package com.mahar.readymadetemplateforandroid.di

import android.content.Context
import com.elemental.atantat.network.ConnectivityInterceptor
import com.elemental.atantat.network.ConnectivityInterceptorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class InterceptorAnnotation


@Module
@InstallIn(SingletonComponent::class)
@Named("ConnectivityInterceptor")
abstract class InterceptorModule {
    @InterceptorAnnotation
    @Singleton
    @Binds
    abstract fun bindConnectivityInterceptor(
        connectivityInterceptorImpl: ConnectivityInterceptorImpl
    ): ConnectivityInterceptor
}