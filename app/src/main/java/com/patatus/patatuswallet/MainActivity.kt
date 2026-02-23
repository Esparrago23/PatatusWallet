package com.patatus.patatuswallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.patatus.patatuswallet.core.ui.theme.PatatusWalletTheme
import android.app.Application
import com.patatus.patatuswallet.core.navigation.NavigationWrapper
import com.patatus.patatuswallet.features.crypto.di.CryptoModule
import com.patatus.patatuswallet.features.crypto.navigation.CryptoNavGraph
import com.patatus.patatuswallet.features.crypto.presentation.screens.CryptoScreen
import com.patatus.patatuswallet.features.portfolio.navigation.PortfolioNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navGraphs = listOf(
            CryptoNavGraph(),
            PortfolioNavGraph()
        )
        enableEdgeToEdge()
        setContent {
            PatatusWalletTheme {
                NavigationWrapper(navGraphs = navGraphs)
            }
        }
    }
}

