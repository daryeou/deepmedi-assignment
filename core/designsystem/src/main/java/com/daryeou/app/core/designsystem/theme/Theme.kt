package com.daryeou.app.core.designsystem.theme

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private object RippleCustomTheme : RippleTheme {
    @Composable
    override fun defaultColor() =
        RippleTheme.defaultRippleColor(
            Color.Red,
            lightTheme = true
        )

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleTheme.defaultRippleAlpha(
            Color.Black,
            lightTheme = true
        )
}

@Composable
fun AppTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        typography = primaryTypography,
        shapes = Shapes,
    ) {
        Surface {
            CompositionLocalProvider(LocalRippleTheme provides RippleCustomTheme) {
                ProvideTextStyle(value = primaryTextStyle) {
                    content()
                }
            }
        }
    }
}