package com.example.domain.di

import com.example.common.Constants
import com.example.domain.data.Api
import com.example.data.PixaApiImpl
import com.example.domain.repo.PixaRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providePixaApi(): Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun providePixaRepository(api: Api): PixaRepo {
        return PixaApiImpl(api)
    }
}