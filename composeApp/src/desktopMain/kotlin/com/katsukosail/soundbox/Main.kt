package com.katsukosail.soundbox

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.katsukosail.soundbox.ui.Home
import org.jetbrains.compose.resources.painterResource
import soundbox.composeapp.generated.resources.Res
import soundbox.composeapp.generated.resources.favicon

fun main() = application {
    val icon = painterResource(Res.drawable.favicon)

    val state = rememberWindowState(
        width = 864.dp,
        height = 650.dp,
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "SoundBox",
        icon = icon,
        state = state
    ) {
        Home()
    }
}