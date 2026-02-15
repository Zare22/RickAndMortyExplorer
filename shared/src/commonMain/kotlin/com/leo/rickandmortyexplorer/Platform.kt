package com.leo.rickandmortyexplorer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform