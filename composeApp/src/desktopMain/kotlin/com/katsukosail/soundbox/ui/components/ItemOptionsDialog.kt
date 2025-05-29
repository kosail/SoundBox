package com.katsukosail.soundbox.ui.components

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import org.jetbrains.compose.resources.painterResource
import soundbox.composeapp.generated.resources.Res
import soundbox.composeapp.generated.resources.add_playlist
import soundbox.composeapp.generated.resources.delete
import soundbox.composeapp.generated.resources.edit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun itemOptionsDialog(
    onDismissRequest: () -> Unit,
    currentScreen: String,
    onAddToPlaylistRequest: () -> Unit,
    onEditRequest: () -> Unit,
    onDeleteRequest: () -> Unit
){
    var type = ""
    if (currentScreen=="Canciones"){
        type = "cancion"
    }else if (currentScreen=="Artistas"){
        type = "artista"
    }else if (currentScreen=="Álbumes"){
        type = "álbum"
    }else if (currentScreen=="Playlist"){
        type = "playlist"
    }else if (currentScreen=="Géneros"){
        type = "género"
    }

    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.width(350.dp).height(200.dp)
    ) {
        Surface (
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(), verticalArrangement = Arrangement.Center
            ) {

                Button(
                    onClick = {onAddToPlaylistRequest},
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background, contentColor = MaterialTheme.colorScheme.onBackground
                    ))
                {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.add_playlist),
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                                .weight(0.3f)
                        )

                        Text(
                            text = "Añadir a playlist",
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(5.dp)
                                .weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {onEditRequest},
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background, contentColor = MaterialTheme.colorScheme.onBackground

                    ))
                {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.edit),
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                                .weight(0.3f)
                        )

                        Text(
                            text = "Editar " + type,
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(5.dp)
                                .weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {onDeleteRequest},
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background, contentColor = MaterialTheme.colorScheme.onBackground

                    ))
                {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.delete),
                            contentDescription = null,
                            modifier = Modifier
                                .size(25.dp)
                                .weight(0.3f)
                        )

                        Text(
                            text = "Eliminar " + type,
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(5.dp)
                                .weight(1f)
                        )
                    }
                }

            }
        }
    }
}

