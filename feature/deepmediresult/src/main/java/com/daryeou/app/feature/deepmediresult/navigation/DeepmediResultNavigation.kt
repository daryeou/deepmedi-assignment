package com.daryeou.app.feature.deepmediresult.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.daryeou.app.feature.deepmediresult.DeepmediResultRoute

const val deepmediResultNavigationRoute = "deepmedi_result_route"

fun NavController.navigateToDeepmediResult(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    this.navigate(deepmediResultNavigationRoute, navOptions)
}

fun NavGraphBuilder.deepmediResultScreen(
    onNavigateToHome: () -> Unit,
    showMessage: (String) -> Unit,
) {
    composable(
        route = deepmediResultNavigationRoute,
    ) { _ ->
        DeepmediResultRoute(
            onNavigateToHome = onNavigateToHome,
            showMessage = showMessage
        )
    }
}