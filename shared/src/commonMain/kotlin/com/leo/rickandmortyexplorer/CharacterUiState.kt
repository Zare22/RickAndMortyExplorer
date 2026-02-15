package com.leo.rickandmortyexplorer

sealed interface CharacterUiState {
    data object Loading: CharacterUiState
    data class Success(val characters: List<Character>) : CharacterUiState
    data class Error(val message: String) : CharacterUiState
}