package com.justinjose.wifigames.data

import androidx.annotation.DrawableRes

data class GameCategoryModel(
    val label: String = "",
    @DrawableRes
    val imgResId: Int = 0,
)
