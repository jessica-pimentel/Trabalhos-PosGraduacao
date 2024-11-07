package com.example.dogapi

import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPI{
    @GET("breed/{breed}/images/random")
    suspend fun getRandomDogImage(@Path("breed") breed: String): DogResponse
}
