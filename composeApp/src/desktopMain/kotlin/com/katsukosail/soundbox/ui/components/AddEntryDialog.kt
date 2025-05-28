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
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.katsukosail.soundbox.Theme.slightDarkBackground
import com.katsukosail.soundbox.Theme.slightLightBackground
import com.katsukosail.soundbox.model.AddEntryForm
import org.jetbrains.compose.resources.painterResource
import soundbox.composeapp.generated.resources.Res
import soundbox.composeapp.generated.resources.music_note
import soundbox.composeapp.generated.resources.queue_music

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEntryDialog (
    onCloseRequest: () -> Unit,

    isAddPlaylist: Boolean,
    onAddToggleChange: () -> Unit,
    entryForm: AddEntryForm,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onAlbumChange: (String) -> Unit,
    onArtistChange: (String) -> Unit,
    onGenreChange: (String) -> Unit,

    darkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    BasicAlertDialog(
        onDismissRequest = onCloseRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface (
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 8.dp,
            color = MaterialTheme.colorScheme.background,
            modifier = modifier
                .size(600.dp, 500.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                tonalElevation = 8.dp,
                color = if (darkTheme) slightDarkBackground else slightLightBackground,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = onCloseRequest,
                        modifier = Modifier
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Close settings",
                            modifier = Modifier
                        )
                    }
                }

                Column (
                    modifier = Modifier
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {
                    Text(
                        text = if (isAddPlaylist) "Añadir nueva playlist" else "Añadir nueva canción",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Canción",
                            color = if (! isAddPlaylist) {
                                MaterialTheme.colorScheme.onSurface
                            }  else {
                                if (darkTheme) Color(0xFFA1A1A1) else Color(0xFF909090)
                            },
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(end = 15.dp)
                        )

                        Switch(
                            checked = isAddPlaylist,
                            onCheckedChange = { onAddToggleChange() },
                            colors = SwitchDefaults.colors(
                                uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                                uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,

                                checkedThumbColor = MaterialTheme.colorScheme.tertiary,
                                checkedTrackColor = MaterialTheme.colorScheme.tertiaryContainer
                            ),
                            thumbContent = {
                                val icon = if (isAddPlaylist) Res.drawable.queue_music else Res.drawable.music_note
                                Icon(
                                        painter = painterResource(icon),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.padding(3.dp)
                                    )
                            },
                            modifier = Modifier
                        )

                        Text(
                            text = "Playlist",
                            textAlign = TextAlign.Start,
                            color = if (isAddPlaylist) {
                                MaterialTheme.colorScheme.onSurface
                            }  else {
                                if (darkTheme) Color(0xFFA1A1A1) else Color(0xFF909090)
                            },
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(start = 15.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    if (isAddPlaylist){
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            TextField(
                                placeholder = {
                                    Text("Nombre de la playlist", modifier = Modifier.padding(start = 10.dp))
                                },
                                value = entryForm.name,
                                onValueChange = onNameChange,
                                shape = RoundedCornerShape(10.dp),
                                maxLines = 1,
                                singleLine = true,
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                                    focusedIndicatorColor = Color.Transparent,    // remove underline (focused)
                                    unfocusedIndicatorColor = Color.Transparent,  // remove underline (unfocused)
                                    disabledIndicatorColor = Color.Transparent,   // remove underline (disabled)
                                ),
                                modifier = Modifier.width(400.dp)
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            TextField(
                                placeholder = {
                                    Text("Descripción", modifier = Modifier.padding(start = 10.dp))
                                },
                                value = entryForm.description,
                                onValueChange = onDescriptionChange,
                                shape = RoundedCornerShape(10.dp),
                                maxLines = 2,
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                                    focusedIndicatorColor = Color.Transparent,    // remove underline (focused)
                                    unfocusedIndicatorColor = Color.Transparent,  // remove underline (unfocused)
                                    disabledIndicatorColor = Color.Transparent,   // remove underline (disabled)
                                ),
                                modifier = Modifier.width(400.dp)
                            )

                            Spacer(modifier = Modifier.height(30.dp))

                            Button(
                                onClick = {},
                                shape = RoundedCornerShape(4.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.tertiary,
                                ),
                                modifier = Modifier
                            ) {
                                Text("Guardar")
                            }
                        }
                    } else {
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            TextField(
                                placeholder = {
                                    Text("Nombre de la canción", modifier = Modifier.padding(start = 10.dp))
                                },
                                value = entryForm.name,
                                onValueChange = onNameChange,
                                shape = RoundedCornerShape(10.dp),
                                maxLines = 1,
                                singleLine = true,
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                                    focusedIndicatorColor = Color.Transparent,    // remove underline (focused)
                                    unfocusedIndicatorColor = Color.Transparent,  // remove underline (unfocused)
                                    disabledIndicatorColor = Color.Transparent,   // remove underline (disabled)
                                ),
                                modifier = Modifier.width(400.dp)
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            TextField(
                                placeholder = {
                                    Text("Álbum", modifier = Modifier.padding(start = 10.dp))
                                },
                                value = entryForm.album,
                                onValueChange = onAlbumChange,
                                shape = RoundedCornerShape(10.dp),
                                maxLines = 1,
                                singleLine = true,
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                                    focusedIndicatorColor = Color.Transparent,    // remove underline (focused)
                                    unfocusedIndicatorColor = Color.Transparent,  // remove underline (unfocused)
                                    disabledIndicatorColor = Color.Transparent,   // remove underline (disabled)
                                ),
                                modifier = Modifier.width(400.dp)
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            TextField(
                                placeholder = {
                                    Text("Artista", modifier = Modifier.padding(start = 10.dp))
                                },
                                value = entryForm.artist,
                                onValueChange = onArtistChange,
                                shape = RoundedCornerShape(10.dp),
                                maxLines = 1,
                                singleLine = true,
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                                    focusedIndicatorColor = Color.Transparent,    // remove underline (focused)
                                    unfocusedIndicatorColor = Color.Transparent,  // remove underline (unfocused)
                                    disabledIndicatorColor = Color.Transparent,   // remove underline (disabled)
                                ),
                                modifier = Modifier.width(400.dp)
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            TextField(
                                placeholder = {
                                    Text("Género", modifier = Modifier.padding(start = 10.dp))
                                },
                                value = entryForm.genre,
                                onValueChange = onGenreChange,
                                shape = RoundedCornerShape(10.dp),
                                maxLines = 1,
                                singleLine = true,
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                                    focusedIndicatorColor = Color.Transparent,    // remove underline (focused)
                                    unfocusedIndicatorColor = Color.Transparent,  // remove underline (unfocused)
                                    disabledIndicatorColor = Color.Transparent,   // remove underline (disabled)
                                ),
                                modifier = Modifier.width(400.dp)
                            )

                            Spacer(modifier = Modifier.height(30.dp))

                            Button(
                                onClick = { },
                                shape = RoundedCornerShape(4.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.secondary,
                                ),
                                modifier = Modifier
                            ) { Text("Guardar") }
                        }
                    }
                }
            }
        }
    }
}