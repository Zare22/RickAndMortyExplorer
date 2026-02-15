package com.leo.rickandmortyexplorer.android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.leo.rickandmortyexplorer.CharacterUiState
import com.leo.rickandmortyexplorer.RickAndMortyApi
import com.leo.rickandmortyexplorer.SharedCharacterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val api: RickAndMortyApi by inject()
    private val viewModel = SharedCharacterViewModel(api, CoroutineScope(Dispatchers.Main))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CharacterScreen(viewModel)
            }
        }
    }
}

@Composable
private fun CharacterScreen(viewModel: SharedCharacterViewModel) {

    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    when (val currentState = state) {
        is CharacterUiState.Loading -> {
            CircularProgressIndicator()
        }
        is CharacterUiState.Error -> {
            Text("Error: ${currentState.message}")
        }
        is CharacterUiState.Success -> {
            LazyColumn {
                items(currentState.characters) { character ->
                    Text(
                        text = character.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                openBrowser(context, character.image)
                            }
                    )
                }
            }
        }
    }
}

fun openBrowser(context: Context, imageUrl: String) {
    val intent = Intent(Intent.ACTION_VIEW, imageUrl.toUri())
    context.startActivity(intent)
}