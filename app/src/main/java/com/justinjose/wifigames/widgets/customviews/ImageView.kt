package com.justinjose.wifigames.widgets.customviews

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun ImageView(@DrawableRes imgResId: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = imgResId),
        contentDescription = null,
    )
}