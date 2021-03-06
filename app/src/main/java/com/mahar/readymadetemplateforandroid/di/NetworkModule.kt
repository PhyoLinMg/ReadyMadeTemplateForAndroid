package com.mahar.readymadetemplateforandroid.di

import android.content.Context
import com.elemental.atantat.network.ConnectivityInterceptor
import com.elemental.atantat.network.ConnectivityInterceptorImpl
import com.elemental.templateapplication.remote.network.services.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    @Named("ApiService")
    fun provideApiService(
        @ApplicationContext context:Context,
        @InterceptorAnnotation connectivityInterceptor: ConnectivityInterceptor
    )=ApiService(connectivityInterceptor,context)
}



