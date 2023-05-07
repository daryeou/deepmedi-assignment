package com.daryeou.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.daryeou.app.navigation.DeepmediNavHost

@Composable
fun DeepmediApp() {
    val navHostController = rememberNavController()

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { contentPadding ->
        Box(
            modifier =
            Modifier.padding(contentPadding)
        ) {
            DeepmediNavHost(
                modifier = Modifier
                    .fillMaxSize(),
                navController = navHostController,
                snackbarHostState = snackbarHostState,
            )
        }
    }
}