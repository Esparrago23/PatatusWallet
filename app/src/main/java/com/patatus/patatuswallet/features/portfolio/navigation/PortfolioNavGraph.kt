package com.patatus.patatuswallet.features.portfolio.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.patatus.patatuswallet.core.navigation.FeatureNavGraph
import com.patatus.patatuswallet.core.navigation.Portfolio
import com.patatus.patatuswallet.features.portfolio.presentation.screens.PortfolioScreen

class PortfolioNavGraph : FeatureNavGraph {
    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavHostController) {
        navGraphBuilder.composable<Portfolio> {
            PortfolioScreen()
        }
    }
}