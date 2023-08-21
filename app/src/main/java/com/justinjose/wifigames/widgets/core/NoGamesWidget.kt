package com.justinjose.wifigames.widgets.core

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.justinjose.wifigames.widgets.customviews.ImageView
import com.justinjose.wifigames.widgets.customviews.TypoBody1

@Composable
fun NoGamesWidget() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            ImageView(
                imgResId = com.justinjose.wifigames.R.drawable.ic_no_internet_light,
                Modifier.size(160.dp)
            )  //NoGamesScreen
            TypoBody1(text = "Sorry, we were unable to fetch any games for you.")   //NoGamesScreen
        }
    }
}