package com.example.queenapi.network

import com.example.queenapi.model.Queen
import retrofit2.http.GET

interface endPoint {

    @GET("queens/all")
    suspend fun getAllQueens() : List<Queen>

}