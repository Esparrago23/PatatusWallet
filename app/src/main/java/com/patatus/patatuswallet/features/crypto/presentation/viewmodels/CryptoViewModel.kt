package com.patatus.patatuswallet.features.crypto.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patatus.patatuswallet.features.crypto.domain.usecases.GetCoinsUseCase
import com.patatus.patatuswallet.features.crypto.presentation.screens.CryptoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.patatus.patatuswallet.features.crypto.domain.entities.CryptoCoin
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
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

    fun onSelectCoinForCalculation(coin: CryptoCoin) {
        _uiState.update {
            it.copy(
                selectedCoinForCalculation = coin,
                calculatorInputAmount = "",
                calculatorResultTokens = 0.0
            )
        }
    }


    fun onDismissCalculator() {
        _uiState.update { it.copy(selectedCoinForCalculation = null) }
    }


    fun onCalculatorInputChange(input: String) {
        if (input.all { char -> char.isDigit() || char == '.' }) {
            _uiState.update { currentState ->
                val amount = input.toDoubleOrNull() ?: 0.0
                val price = currentState.selectedCoinForCalculation?.currentPrice ?: 0.0

                val tokens = if (price > 0) amount / price else 0.0

                currentState.copy(
                    calculatorInputAmount = input,
                    calculatorResultTokens = tokens
                )
            }
        }
    }
}