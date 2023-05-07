package com.daryeou.app.navigation

import com.daryeou.app.feature.deepmedihome.navigation.deepmediHomeNavigationRoute
import com.daryeou.app.feature.deepmediresult.navigation.deepmediResultNavigationRoute


enum class TopLevelDestination(
    val route: String,
) {
    Home(
        deepmediHomeNavigationRoute,
    ),
    Result(
        deepmediResultNavigationRoute,
    ),
}