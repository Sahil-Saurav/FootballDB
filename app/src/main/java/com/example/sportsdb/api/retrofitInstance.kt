package com.example.sportsdb.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitInstance {

    private const val baseUrl = "https://www.thesportsdb.com/api/v1/json/3/"

    fun getInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val sportsApi :SportsApi = getInstance().create(SportsApi::class.java)
}