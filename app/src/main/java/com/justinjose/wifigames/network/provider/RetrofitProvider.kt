package com.justinjose.wifigames.network.provider

import com.justinjose.wifigames.BuildConfig
import com.justinjose.wifigames.network.services.GamesService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitProvider {
    private var retrofit: Retrofit
    private val okHttpBuilder = OkHttpClient.Builder()
    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    init {
        okHttpBuilder
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(loggingInterceptor)
        }

        retrofit = Retrofit.Builder()
            .baseUrl("https://pub.gamezop.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }

    var gamesService: GamesService = retrofit.create(GamesService::class.java)
}