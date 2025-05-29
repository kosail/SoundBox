package com.katsukosail.soundbox.model

data class Playlist(
    val id: Int,
    val name: String,
    val date: String,
    val description: String
)

val playlists: List<Playlist> = listOf(
)