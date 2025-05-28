package com.katsukosail.soundbox

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.katsukosail.soundbox.database.DatabaseConnection
import com.katsukosail.soundbox.ui.Home
import com.katsukosail.soundbox.ui.InitializeServices
import org.jetbrains.compose.resources.painterResource
import soundbox.composeapp.generated.resources.Res
import soundbox.composeapp.generated.resources.favicon

fun main() = application {
    val icon = painterResource(Res.drawable.favicon)

    Window(
        onCloseRequest = ::exitApplication,
        title = "SoundBox",
        icon = icon,
        state = WindowState( size = DpSize(864.dp, 650.dp) )
    ) {
        // Commented out because there is an issue with the DB, throwing ClassNotFoundException
//        InitializeServices(exitApp = ::exitApplication) // Initialize database connection or exit the app
        Home() // Start the app if all went OK
    }
}