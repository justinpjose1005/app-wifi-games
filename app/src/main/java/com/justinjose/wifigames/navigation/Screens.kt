package com.justinjose.wifigames.navigation

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")
    object Dashboard : Screens("dashboard")
}
