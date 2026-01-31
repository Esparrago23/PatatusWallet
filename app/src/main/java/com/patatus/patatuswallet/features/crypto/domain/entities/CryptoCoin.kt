package com.patatus.patatuswallet.features.crypto.domain.entities

data class CryptoCoin(
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    val currentPrice: Double,
    val priceChangePercentage24h: Double
)