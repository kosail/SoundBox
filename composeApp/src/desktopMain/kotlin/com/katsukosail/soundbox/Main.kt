package com.katsukosail.soundbox

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.katsukosail.soundbox.state.DbDonnectionState
import com.katsukosail.soundbox.ui.Home
import com.katsukosail.soundbox.ui.DbFailInit
import org.jetbrains.compose.resources.painterResource
import soundbox.composeapp.generated.resources.Res
import soundbox.composeapp.generated.resources.favicon

fun main() = application {
    val icon = painterResource(Res.drawable.favicon)
    val dbState = remember { DbDonnectionState() }

    Window(
        onCloseRequest = {
            dbState.close()
            ::exitApplication
        },
        title = "SoundBox",
        icon = icon,
        state = WindowState( size = DpSize(864.dp, 650.dp) )
    ) {

        // Store the DB instance reference across recompositions
        val dbInitialized by remember { mutableStateOf(dbState.initializeDatabase()) }

        if (!dbInitialized) {
            DbFailInit(exitApp = ::exitApplication)
        }

        Home()
    }
}