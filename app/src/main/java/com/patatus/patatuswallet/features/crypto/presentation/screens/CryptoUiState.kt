package com.patatus.patatuswallet.features.crypto.presentation.screens

import com.patatus.patatuswallet.features.crypto.domain.entities.CryptoCoin
data class CryptoUiState(
    val isLoading: Boolean = false,
    val coins: List<CryptoCoin> = emptyList(),
    val error: String? = null
)