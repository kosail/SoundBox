package com.katsukosail.soundbox.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.katsukosail.soundbox.Theme.slightDarkBackground
import com.katsukosail.soundbox.Theme.slightLightBackground
import com.katsukosail.soundbox.model.ItemType

@Composable
fun EditItemsDialogs (item: ItemType, onDismissRequest: () -> Unit) {
    when (val item = item) {
        is ItemType.SongItem -> {

        }
        is ItemType.AlbumItem -> TODO()
        is ItemType.ArtistItem -> TODO()
        is ItemType.GenreItem -> TODO()
        is ItemType.PlaylistItem -> TODO()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDialog(
    onCloseRequest: () -> Unit,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onAlbumChange: (String) -> Unit,
    onArtistChange: (String) -> Unit,
    onGenreChange: (String) -> Unit,
    onSaveChanges: () -> Unit,
    darkTheme: Boolean
) {
    BasicAlertDialog(
        onDismissRequest = onCloseRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 8.dp,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.size(600.dp, 500.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                tonalElevation = 8.dp,
                color = if (darkTheme) slightDarkBackground else slightLightBackground,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = onCloseRequest
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "Cerrar edición"
                            )
                        }
                    }

                    Text(
                        text = if (isPlaylist) "Editar playlist" else "Editar canción",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))


                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = onSaveChanges,
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Guardar cambios")
                    }
                }
            }
        }
    }
}
