package com.katsukosail.soundbox.model

data class Genre(
    val id: Int,
    val name: String
)

val genres: List<Genre> = listOf(
    Genre(id = 1, name = "City Pop"),
    Genre(id = 2, name = "Indie Pop"),
    Genre(id = 3, name = "J-Pop"),
    Genre(id = 4, name = "Ballad"),
    Genre(id = 5, name = "Pop Rock"),
    Genre(id = 6, name = "Alternative Pop"),
    Genre(id = 7, name = "Anime Music")
)
