package com.justinjose.wifigames.widgets.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.justinjose.wifigames.widgets.customviews.ImageView
import com.justinjose.wifigames.widgets.customviews.TypoBody1
import com.justinjose.wifigames.widgets.reuse.ToolBar

@Composable
fun NoInternetWidget() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
    ) {
        ToolBar()   //NoInternetScreen
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp),
            ) {
                ImageView(  //NoInternetScreen
                    imgResId = com.justinjose.wifigames.R.drawable.ic_no_internet_light,
                    Modifier.size(160.dp)
                )
                TypoBody1("wifigames...is....not......responding!\nChEcK yOuR iNtErNeT") //NoInternetScreen
            }
        }
    }
}