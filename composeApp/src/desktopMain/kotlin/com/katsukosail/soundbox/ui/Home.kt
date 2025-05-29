package com.katsukosail.soundbox.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.katsukosail.soundbox.Theme.CustomTheme
import com.katsukosail.soundbox.Theme.rememberAppThemeState
import com.katsukosail.soundbox.model.AddEntryForm
import com.katsukosail.soundbox.ui.components.*

@Composable
fun Home(){
    val appThemeState = rememberAppThemeState()

    var addFormState by remember {
        mutableStateOf(AddEntryForm())
    }

    CustomTheme(darkTheme = appThemeState.isDarkTheme) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            var currentScreen by remember { mutableStateOf("Canciones") }
            var searchInput by remember { mutableStateOf("") }

            var isDrawerOpened by remember { mutableStateOf(false) }
            var isSettingsDialogOpen by remember { mutableStateOf(false) }

            // Para abrir y cerrar la ventana de nueva canción/playlist y cambiar entre modo canción y playlist
            var isAddDialogOpen by remember { mutableStateOf(false) }

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
                    add = {
                        isAddDialogOpen = true
                        addFormState = AddEntryForm() // Resetea todos los campos
                    }
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

                if (isAddDialogOpen) {
                    AddEntryDialog(
                        onCloseRequest = {
                            isAddDialogOpen = false
                            addFormState = AddEntryForm() // Resetea todos los campos
                        },
                        isAddPlaylist = addFormState.isPlaylist,
                        onAddToggleChange = {
                            addFormState = addFormState.copy(isPlaylist = !addFormState.isPlaylist)
                        },
                        darkTheme = appThemeState.isDarkTheme,
                        entryForm = addFormState,
                        onNameChange = { addFormState = addFormState.copy(name = it) },
                        onDescriptionChange = { addFormState = addFormState.copy(description = it) },
                        onAlbumChange = { addFormState = addFormState.copy(album = it) },
                        onGenreChange = { addFormState = addFormState.copy(genre = it) },
                        onArtistChange = { addFormState = addFormState.copy(artist = it) }
                    )
                }
            }
        }
    }
}