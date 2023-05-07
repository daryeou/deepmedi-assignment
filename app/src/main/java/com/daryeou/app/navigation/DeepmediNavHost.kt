package com.daryeou.app.navigation

import android.app.Activity
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.daryeou.app.feature.deepmedihome.navigation.deepmediHomeScreen
import com.daryeou.app.feature.deepmediresult.navigation.deepmediResultScreen
import com.daryeou.app.feature.deepmediresult.navigation.navigateToDeepmediResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun DeepmediNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
) {
    val coroutineScope = rememberCoroutineScope()
    val startDestination = TopLevelDestination.Home.route

    val activity = LocalContext.current as Activity

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        deepmediHomeScreen(
            onNavigateToResult = {
                navController.navigateToDeepmediResult()
            },
            onExit = {
                activity.finish()
            },
            showMessage = { text ->
                snackbarHostState.showMessage(
                    coroutineScope = coroutineScope,
                    text = text,
                )
            },
        )
        deepmediResultScreen(
            onNavigateToHome = {
                navController.popBackStack(startDestination, inclusive = false)
            },
            showMessage = { text ->
                snackbarHostState.showMessage(
                    coroutineScope = coroutineScope,
                    text = text,
                )
            },
        )
    }
}

private fun SnackbarHostState.showMessage(
    coroutineScope: CoroutineScope,
    text: String,
) {
    coroutineScope.launch {
        currentSnackbarData?.dismiss()
        showSnackbar(text)
    }
}
