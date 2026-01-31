package com.patatus.patatuswallet.features.crypto.data.datasources.remote.mapper

import com.patatus.patatuswallet.features.crypto.data.datasources.remote.model.CryptoDto
import com.patatus.patatuswallet.features.crypto.domain.entities.CryptoCoin

fun CryptoDto.toDomain(): CryptoCoin {
    return CryptoCoin(
        id = this.id,
        symbol = this.symbol,
        name = this.name,
        image = this.image,
        currentPrice = this.currentPrice,
        priceChangePercentage24h = this.priceChangePercentage24h
    )
}