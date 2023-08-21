package com.justinjose.wifigames.others

import com.justinjose.wifigames.ui.viewmodels.GamesViewModel

object AppUtils {
    fun getIndexListViaListSize(size: Int): List<GameIndex> {
        if (size == 0) {
            return listOf()
        }
        var startIndex = 1
        var endIndex = GamesViewModel.MAX_ITEMS_PER_PAGE
        val indexList = mutableListOf<GameIndex>()
        while (endIndex < size) {
            indexList.add(GameIndex(startIndex, endIndex))
            startIndex += GamesViewModel.MAX_ITEMS_PER_PAGE
            endIndex += GamesViewModel.MAX_ITEMS_PER_PAGE
        }
        indexList.add(GameIndex(startIndex, size))
        return indexList
    }
}

data class GameIndex(
    val startIndex: Int = 0,
    val endIndex: Int = 0,
    var isSelected: Boolean = false,
)