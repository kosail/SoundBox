package com.katsukosail.soundbox.model

import org.jetbrains.compose.resources.DrawableResource
import soundbox.composeapp.generated.resources.Res
import soundbox.composeapp.generated.resources.artists

data class Album(
    val id: Int,
    val artist: Artist,
    val name: String,
    val date: String,
    val image: DrawableResource
)

val albums: List<Album> = listOf(
    // Lamp
    Album(1, artists[0], "Tokyo Utopia", "2004-06-23", Res.drawable.artists),
    Album(2, artists[0], "For Lovers", "2007-09-12", Res.drawable.artists),
    Album(3, artists[0], "Her Watch", "2014-10-29", Res.drawable.artists),

    // Laura Day Romance
    Album(4, artists[1], "Fantasy Love", "2019-04-17", Res.drawable.artists),
    Album(5, artists[1], "Midnight Garden", "2021-11-03", Res.drawable.artists),

    // Aimer
    Album(6, artists[2], "Daydream", "2016-09-21", Res.drawable.artists),
    Album(7, artists[2], "Sun Dance", "2019-04-10", Res.drawable.artists),
    Album(8, artists[2], "Walpurgis", "2021-04-14", Res.drawable.artists),

    // Back Number
    Album(9, artists[3], "Chandelier", "2015-12-09", Res.drawable.artists),
    Album(10, artists[3], "Encore", "2018-12-20", Res.drawable.artists),

    // YOASOBI
    Album(11, artists[4], "The Book", "2021-01-06", Res.drawable.artists),
    Album(12, artists[4], "The Book 2", "2021-12-01", Res.drawable.artists),
    Album(13, artists[4], "E-SIDE", "2021-11-12", Res.drawable.artists),

    // Aimyon
    Album(14, artists[5], "Shunkanteki Sixth Sense", "2019-02-13", Res.drawable.artists),
    Album(15, artists[5], "Falling Into Your Eyes Record", "2022-08-17", Res.drawable.artists),
    Album(16, artists[5], "Oishii Pasta ga Aru to Kiite", "2020-09-09", Res.drawable.artists)
)
