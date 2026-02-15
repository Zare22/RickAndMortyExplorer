package com.leo.rickandmortyexplorer

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RickAndMortyApi(private  val client: HttpClient) {

    suspend fun fetchCharacters(): List<Character> {
        return try {
            val response: CharacterResponse = client.get("https://rickandmortyapi.com/api/character").body()
            response.results
        } catch (e: Exception) {
            emptyList()
        }
    }
}