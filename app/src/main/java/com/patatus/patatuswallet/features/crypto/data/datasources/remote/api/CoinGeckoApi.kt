package com.patatus.patatuswallet.features.crypto.data.datasources.remote.api

import com.patatus.patatuswallet.features.crypto.data.datasources.remote.model.CryptoDto
import retrofit2.http.GET

import retrofit2.http.POST
import retrofit2.http.Query

interface CoinGeckoApi {
    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String = "mxn",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") perPage: Int = 20,
        @Query("page") page: Int = 1,
        @Query("sparkline") sparkline: Boolean = false,
        @Query("x_cg_demo_api_key") apiKey: String = "tu apikey aqui"
    ):List<CryptoDto>
}