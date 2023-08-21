package com.justinjose.wifigames.network.services

import com.justinjose.wifigames.utilities.AppConstants
import com.justinjose.wifigames.data.GameZopResponse
import retrofit2.Response
import retrofit2.http.GET

interface GamesService {
    @GET("/v3/games?id=${AppConstants.PROPERTY_ID}&lang=LOCALE")
    suspend fun getAllGames(): Response<GameZopResponse>
}