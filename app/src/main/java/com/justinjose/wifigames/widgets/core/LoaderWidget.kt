package com.justinjose.wifigames.widgets.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.justinjose.wifigames.widgets.reuse.LoadingScreenProgressBar
import com.justinjose.wifigames.widgets.reuse.ToolBar

@Composable
fun LoaderWidget() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        ToolBar()   //LoadingScreen
        LoadingScreenProgressBar()  //LoadingScreen
    }
}