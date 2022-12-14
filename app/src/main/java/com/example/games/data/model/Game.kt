package com.example.games.data.model

data class Game(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val short_description: String,
    val genre: String,
    val platform: String,
    val release_date: String,
    val freetogame_profile_url: String,
    var is_fav: Boolean = false
)
