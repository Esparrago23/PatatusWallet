package com.patatus.patatuswallet.core.di

import android.content.Context
import com.patatus.patatuswallet.core.network.CoinGeckoApi
import com.patatus.patatuswallet.features.crypto.data.repositories.CryptoRepositoryImpl
import com.patatus.patatuswallet.features.crypto.domain.repositories.CryptoRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AppContainer( context: Context)  {

    private val baseUrl = "https://api.coingecko.com/api/v3/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    private val coinGeckoApi: CoinGeckoApi by lazy {
        retrofit.create(CoinGeckoApi::class.java)
    }

     val cryptoRepository: CryptoRepository by lazy {
        CryptoRepositoryImpl(coinGeckoApi)
    }
}

