package com.leo.rickandmortyexplorer

import io.ktor.client.HttpClient
import io.ktor.utils.io.concurrent.shared
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import sharedModule

fun initKoin() {
    startKoin {
        modules(sharedModule)
    }
}

object IOSViewModelProvider {
    fun getViewModel(): SharedCharacterViewModel {
        return SharedCharacterViewModel(
            api = RickAndMortyApi(HttpClient()),
            coroutineScope = CoroutineScope(Dispatchers.Main)
        )
    }
}