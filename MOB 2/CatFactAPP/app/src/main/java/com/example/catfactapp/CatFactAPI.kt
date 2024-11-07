package com.example.catfactapp

import retrofit2.http.GET

interface CatFactAPI {
    @GET("fact")
    suspend fun getCatFact(): CatFact
}