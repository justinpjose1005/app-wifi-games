package com.justinjose.wifigames.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.justinjose.wifigames.widgets.core.DashboardWidget
import com.justinjose.wifigames.widgets.core.SplashScreenWidget

@Composable
fun SetupNavController(activity: Activity, navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route) {
            SplashScreenWidget(navController = navController)
        }
        composable(Screens.Dashboard.route) {
            DashboardWidget(activity)
        }
    }
}