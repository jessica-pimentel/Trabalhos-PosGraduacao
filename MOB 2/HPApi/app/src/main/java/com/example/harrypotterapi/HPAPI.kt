package com.example.harrypotterapi

import retrofit2.http.GET
import retrofit2.http.Path

interface HPAPI {
    @GET("api/character/{id}")
    suspend fun getCharacterById(@Path("id") id: String): List<CharacterResponse>

    @GET("api/characters/staff")
    suspend fun getHogwartsStaff(): List<CharacterResponse>

    @GET("api/characters/house/{house}")
    suspend fun getStudentsByHouse(@Path("house") house: String): List<CharacterResponse>
}