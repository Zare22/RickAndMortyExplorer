package com.leo.rickandmortyexplorer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedCharacterViewModel(
    private val api: RickAndMortyApi,
    private val coroutineScope: CoroutineScope
) {

    private val _uiState = MutableStateFlow<CharacterUiState>(CharacterUiState.Loading)
    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        coroutineScope.launch {
            _uiState.value = CharacterUiState.Loading

            try {
                val characters = api.fetchCharacters()
                _uiState.value = CharacterUiState.Success(characters)
            }
            catch (e: Exception) {
                _uiState.value = CharacterUiState.Error(e.message ?: "An unknown error occurred!")
            }
        }
    }
}