package com.leo.rickandmortyexplorer

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val results: List<Character>
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val species: String,
    val image: String
)