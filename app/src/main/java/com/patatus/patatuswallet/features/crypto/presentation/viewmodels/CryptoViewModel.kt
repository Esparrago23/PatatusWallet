package com.patatus.patatuswallet.features.crypto.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patatus.patatuswallet.features.crypto.domain.usecases.GetCoinsUseCase
import com.patatus.patatuswallet.features.crypto.presentation.screens.CryptoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CryptoViewModel(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CryptoUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadCoins()
    }

    fun loadCoins() {
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val result = getCoinsUseCase()

            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { coinList ->
                        currentState.copy(
                            isLoading = false,
                            coins = coinList,
                            error = null
                        )
                    },
                    onFailure = { exception ->
                        currentState.copy(
                            isLoading = false,
                            error = exception.message ?: "Error Escondido"
                        )
                    }
                )
            }
        }
    }
}