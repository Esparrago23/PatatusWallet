package com.patatus.patatuswallet.features.crypto.di

import com.patatus.patatuswallet.core.di.CoinGeckoRetrofit
import com.patatus.patatuswallet.features.crypto.data.datasources.remote.api.CoinGeckoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinGeckoNetworkModule {
    @Provides
    @Singleton
    fun provideCoinGeckoApi(@CoinGeckoRetrofit retrofit: Retrofit): CoinGeckoApi {
        return retrofit.create(CoinGeckoApi::class.java)
    }
}