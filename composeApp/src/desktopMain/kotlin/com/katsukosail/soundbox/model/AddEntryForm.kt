package com.katsukosail.soundbox.model

data class AddEntryForm (
    val name: String = "",
    val description: String = "",
    val album: String = "",
    val genre: String = "",
    val artist: String = "",
    val isPlaylist: Boolean = false
)