package com.justinjose.wifigames.data

import com.justinjose.wifigames.data.gamezop_sub_models.Game

data class GameZopResponse(
    val games: List<Game>,
    val publisherName: String
)