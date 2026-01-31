package com.patatus.patatuswallet.features.crypto.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.patatus.patatuswallet.features.crypto.presentation.components.CryptoItem
import com.patatus.patatuswallet.features.crypto.presentation.viewmodels.CryptoViewModel
import com.patatus.patatuswallet.features.crypto.presentation.viewmodels.CryptoViewModelFactory
import com.patatus.patatuswallet.features.crypto.presentation.components.CalculatorDialog
@Composable
fun CryptoScreen(
    factory: CryptoViewModelFactory,
    modifier: Modifier = Modifier
) {
    val viewModel: CryptoViewModel = viewModel(factory = factory)
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect (Unit) {
        viewModel.loadCoins()
    }
    Scaffold(modifier = modifier.fillMaxSize(),
        topBar = {
            @OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
            androidx.compose.material3.CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Patatus Wallet",
                        fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    IconButton(onClick = { viewModel.loadCoins() }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Actualizar precios"
                        )
                    }
                }
            )
        }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            if (state.coins.isNotEmpty()) {
                LazyColumn {
                    items(state.coins) { coin ->
                        CryptoItem(
                            coin = coin,
                            onClick = {
                                viewModel.onSelectCoinForCalculation(it)
                            }
                        )
                    }
                }
            }
            state.selectedCoinForCalculation?.let { coin ->
                CalculatorDialog(
                    coin = coin,
                    inputAmount = state.calculatorInputAmount,
                    resultTokens = state.calculatorResultTokens,
                    onInputChange = { viewModel.onCalculatorInputChange(it) },
                    onDismiss = { viewModel.onDismissCalculator() }
                )
            }

            state.error?.let { errorMsg ->
                Text(
                    text = errorMsg,
                    color = androidx.compose.ui.graphics.Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }


        }
    }
}
