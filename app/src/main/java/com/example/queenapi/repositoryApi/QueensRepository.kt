package com.example.queenapi.repositoryApi

import com.example.queenapi.network.ApiRetrofit
import com.example.queenapi.network.endPoint

class QueensRepository {
    private val url = "https://www.nokeynoshade.party/api/"
    private val service = endPoint::class

    private val queenService = ApiRetrofit(url).create(service)

    suspend fun getAllQueens() = queenService.getAllQueens()

}