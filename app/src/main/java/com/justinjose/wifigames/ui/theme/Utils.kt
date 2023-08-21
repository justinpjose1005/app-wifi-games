package com.justinjose.wifigames.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ShowSystemsBars(flag: Boolean, barColor: Color) {
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setSystemBarsColor(barColor)
        isSystemBarsVisible = flag
        isStatusBarVisible = flag
        isNavigationBarVisible = flag
    }
}