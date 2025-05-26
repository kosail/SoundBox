package com.katsukosail.soundbox.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.katsukosail.soundbox.Theme.CustomTheme
import com.katsukosail.soundbox.Theme.rememberAppThemeState

import com.katsukosail.soundbox.ui.components.TopBar
import com.katsukosail.soundbox.ui.components.MainScreen
import com.katsukosail.soundbox.ui.components.SideMenu
import com.katsukosail.soundbox.ui.components.SettingsDialog

@Composable
fun Home(){
    val appThemeState = rememberAppThemeState()

    CustomTheme(darkTheme = appThemeState.isDarkTheme) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            var currentScreen by remember { mutableStateOf("Canciones") }
            var searchInput by remember { mutableStateOf("") }

            var isDrawerOpened by remember { mutableStateOf(false) }
            var isSettingsDialogOpen by remember { mutableStateOf(false) }

            TopBar(
                searchValue = searchInput,
                searchOnValueChange = { searchInput = it },
                modifier = Modifier,
                onOpenDrawer = { isDrawerOpened = !isDrawerOpened },
                onOpenSettings = { isSettingsDialogOpen = true } // Siempre abre
            )

            Row(
                modifier = Modifier.weight(1f)
            ) {
                SideMenu(
                    isDrawerOpened,
                    appThemeState.isDarkTheme,
                    showSongs = { currentScreen = "Canciones" },
                    showArtists = { currentScreen = "Artistas" },
                    showAlbums = { currentScreen = "Álbumes" },
                    showGenres = { currentScreen = "Géneros" },
                    showPlaylists = { currentScreen = "Playlists" },
                    add = { /*TODO*/ }
                )

                MainScreen(currentScreen)

                if (isSettingsDialogOpen) {
                    SettingsDialog(
                        onCloseRequest = { isSettingsDialogOpen = false }, // Siempre cierra
                        darkTheme = appThemeState.isDarkTheme,
                        onThemeChange = { appThemeState.toggleTheme() },
                        modifier = Modifier
                    )
                }
            }
        }
    }
}