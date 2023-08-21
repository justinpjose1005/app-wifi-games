package com.justinjose.wifigames.repositories

import com.justinjose.wifigames.data.GameZopResponse
import com.justinjose.wifigames.network.provider.RetrofitProvider

object GamesRepo {
    private val mGamesService = RetrofitProvider.gamesService
    suspend fun getGames(): GameZopResponse? {
        runCatching {
            val response = mGamesService.getAllGames()
            if (response.isSuccessful) {
                return response.body()
            }
            return null
        }.getOrElse {
            return null
        }
    }
}