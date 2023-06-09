package com.daryeou.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.daryeou.app.core.designsystem.theme.AppTheme
import com.daryeou.app.ui.DeepmediApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeepmediActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    DeepmediApp()
                }
            }
        }
    }
}