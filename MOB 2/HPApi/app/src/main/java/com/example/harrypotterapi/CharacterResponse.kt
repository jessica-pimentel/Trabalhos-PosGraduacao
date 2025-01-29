package com.example.harrypotterapi

data class CharacterResponse(
    val id: String,
    val name: String,
    val house: String? = null
)
