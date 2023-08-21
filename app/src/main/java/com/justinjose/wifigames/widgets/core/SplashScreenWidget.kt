package com.justinjose.wifigames.widgets.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.justinjose.wifigames.R
import com.justinjose.wifigames.navigation.Screens
import com.justinjose.wifigames.ui.theme.ShowSystemsBars
import kotlinx.coroutines.delay

@Composable
fun SplashScreenWidget(navController: NavController) {
    ShowSystemsBars(flag = true, barColor = MaterialTheme.colors.primary)
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(Screens.Dashboard.route) {
            popUpTo(0)
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.splash_sreen_lottie)
            )
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )
        }
    }
}