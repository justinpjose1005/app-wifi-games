package com.justinjose.wifigames.widgets.reuse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.justinjose.wifigames.ui.viewmodels.GamesViewModel
import com.justinjose.wifigames.widgets.customviews.TypoH1

@Composable
fun ToolBar(gamesViewModel: GamesViewModel = viewModel()) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colors.primary)
            .padding(18.dp, 16.dp, 18.dp, 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TypoH1(text = "wifigames", modifier = Modifier.wrapContentSize())
//        ImageButtonView(
//            imgResId = if (gamesViewModel.isDarkTheme.collectAsState().value) {
//                com.justinjose.wifigames.R.drawable.dark_theme
//            } else {
//                com.justinjose.wifigames.R.drawable.light_theme
//            },
//            modifier = Modifier
//                .size(40.dp)
//                .clickable {
//                    gamesViewModel.updateTheme()
//                }
//        )
    }
}
