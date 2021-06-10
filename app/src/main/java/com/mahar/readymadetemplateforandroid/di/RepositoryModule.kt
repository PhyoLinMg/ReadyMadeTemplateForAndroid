package com.mahar.readymadetemplateforandroid.di

import com.elemental.templateapplication.remote.network.services.ApiService
import com.mahar.readymadetemplateforandroid.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
     @Named("ApiService") apiService: ApiService
    )= MainRepository(apiService)
}