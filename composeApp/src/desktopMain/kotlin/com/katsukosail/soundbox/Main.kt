package com.katsukosail.soundbox

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.katsukosail.soundbox.ui.Home
import org.jetbrains.compose.resources.painterResource
import soundbox.composeapp.generated.resources.Res
import soundbox.composeapp.generated.resources.favicon

fun main() = application {
    val icon = painterResource(Res.drawable.favicon)

    Window(
        onCloseRequest = ::exitApplication,
        title = "SoundBox",
        icon = icon
    ) {
        Home()
    }
}