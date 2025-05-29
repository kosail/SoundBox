package com.katsukosail.soundbox.model

import org.jetbrains.compose.resources.DrawableResource
import soundbox.composeapp.generated.resources.Res
import soundbox.composeapp.generated.resources.artists

data class Artist(
    val id: Int,
    val name: String,
    val image: DrawableResource
)

val artists: List<Artist> = listOf(
    Artist(id = 1, name = "Lamp", image = Res.drawable.artists),
    Artist(id = 2, name = "Laura Day Romance", image = Res.drawable.artists),
    Artist(id = 3, name = "Aimer", image = Res.drawable.artists),
    Artist(id = 4, name = "Back Number", image = Res.drawable.artists),
    Artist(id = 5, name = "YOASOBI", image = Res.drawable.artists),
    Artist(id = 6, name = "Aimyon", image = Res.drawable.artists)
)