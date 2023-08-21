package com.justinjose.wifigames.data

import androidx.annotation.DrawableRes

data class GameDetailsModel(
    val title: String = "",
    val description: String = "",
    @DrawableRes
    val imgResId: Int = 0,
)
