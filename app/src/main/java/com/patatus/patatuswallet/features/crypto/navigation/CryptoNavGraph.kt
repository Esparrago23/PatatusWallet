package com.patatus.patatuswallet.features.crypto.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.patatus.patatuswallet.core.navigation.Crypto
import com.patatus.patatuswallet.core.navigation.FeatureNavGraph
import com.patatus.patatuswallet.features.crypto.presentation.screens.CryptoScreen

class CryptoNavGraph : FeatureNavGraph {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<Crypto> {
            CryptoScreen()
        }
    }
}