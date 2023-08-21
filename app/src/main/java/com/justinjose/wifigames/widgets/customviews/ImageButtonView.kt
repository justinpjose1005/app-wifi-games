package com.justinjose.wifigames.widgets.customviews

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ImageButtonView(@DrawableRes imgResId: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .wrapContentSize()
    ) {
        ImageView(
            imgResId = imgResId,
            modifier = modifier
        )
    }
}