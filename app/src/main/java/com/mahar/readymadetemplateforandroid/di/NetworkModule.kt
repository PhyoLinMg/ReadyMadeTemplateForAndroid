package com.mahar.readymadetemplateforandroid.di

import android.content.Context
import com.elemental.atantat.network.ConnectivityInterceptor
import com.elemental.atantat.network.ConnectivityInterceptorImpl
import com.elemental.templateapplication.remote.network.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    @Named("ApiService")
    fun provideApiService(
        @ApplicationContext context:Context,
        @Named("ConnectivityInterceptor") connectivityInterceptor: ConnectivityInterceptor
    )=ApiService(connectivityInterceptor,context)

    @Singleton
    @Provides
    @Named("ConnectivityInterceptor")
    fun provideConnectivityInterceptor(
        @ApplicationContext context:Context
    )= ConnectivityInterceptorImpl(context);


}