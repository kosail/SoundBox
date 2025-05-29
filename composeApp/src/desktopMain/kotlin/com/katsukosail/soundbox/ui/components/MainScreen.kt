package com.katsukosail.soundbox.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.katsukosail.soundbox.Theme.slightDarkBackground
import com.katsukosail.soundbox.Theme.slightLightBackground
import com.katsukosail.soundbox.model.ItemType
import com.katsukosail.soundbox.model.songs
import com.katsukosail.soundbox.model.artists
import com.katsukosail.soundbox.model.albums
import com.katsukosail.soundbox.model.genres
import com.katsukosail.soundbox.model.playlists
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import soundbox.composeapp.generated.resources.Res
import soundbox.composeapp.generated.resources.add
import soundbox.composeapp.generated.resources.add_playlist
import soundbox.composeapp.generated.resources.delete
import soundbox.composeapp.generated.resources.edit
import soundbox.composeapp.generated.resources.more
import soundbox.composeapp.generated.resources.no_data

@Composable
fun MainScreen(
    currentScreen: String,
    modifier: Modifier = Modifier
) {
    var isPlayingDialogOpen by remember { mutableStateOf(false) }
    var isItemOptionsDialogOpen by remember { mutableStateOf(false) }
    var isEditDialogOpen by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<ItemType?>(null) }

    Surface(
        modifier = modifier
            .fillMaxSize()
    ){
        when (currentScreen) {
            "Canciones" -> ListScreen(
                items = songs,
                loading = false,
                currentScreen,
                itemContent = { song ->
                    SongItem(
                        image = song.album.image,
                        title = song.name,
                        subtitle = song.album.artist.name,
                        onDoubleClick = {
                            selectedItem = ItemType.SongItem(song)

                            isPlayingDialogOpen = true
                        },
                        onOptionClick = {
                            selectedItem = ItemType.SongItem(song)
                            isItemOptionsDialogOpen = true
                        }
                    )
                }
            )

            "Álbumes" -> ListScreen(
                items = albums,
                loading = false,
                currentScreen,
                itemContent = { album ->
                    SongItem(
                        image = album.image,
                        title = album.name,
                        subtitle = "${album.date} • ${album.artist.name}",
                        onDoubleClick = { /* abrir detalles */ },
                        onOptionClick = {
                            selectedItem = ItemType.AlbumItem(album)
                            isItemOptionsDialogOpen = true
                        }
                    )
                }
            )

            "Artistas" -> ListScreen(
                items = artists,
                loading = false,
                currentScreen,
                itemContent = { artist ->
                    SongItem(
                        image = artist.image,
                        title = artist.name,
                        subtitle = "",
                        onDoubleClick = { /* abrir artista */ },
                        onOptionClick = {
                            selectedItem = ItemType.ArtistItem(artist)
                            isItemOptionsDialogOpen = true
                        }
                    )
                }
            )

            "Géneros" -> ListScreen(
                items = genres,
                loading = false,
                currentScreen,
                itemContent = { genre ->
                    SongItem(
                        image = null,
                        title = genre.name,
                        subtitle = "",
                        onDoubleClick = { /* abrir género */ },
                        onOptionClick = {
                            selectedItem = ItemType.GenreItem(genre)
                            isItemOptionsDialogOpen = true
                        }
                    )
                }
            )

            "Playlists" -> ListScreen(
                items = playlists,
                loading = false,
                currentScreen,
                itemContent = { playlist ->
                    SongItem(
                        image = null,
                        title = playlist.name,
                        subtitle = playlist.description,
                        onDoubleClick = { /* abrir playlist */ },
                        onOptionClick = {
                            selectedItem = ItemType.PlaylistItem(playlist)
                            isItemOptionsDialogOpen = true
                        }
                    )
                }
            )
        }
    }

    if (isPlayingDialogOpen && selectedItem != null) {
        when (val item = selectedItem) {
            is ItemType.SongItem -> {
                PlaySongDialog(
                    item = item,
                    onCloseRequest = {
                        isPlayingDialogOpen = false
                        selectedItem = null
                    }
                )
            }
            else -> {
                // Case not reachable
            }
        }
    }

    if (isItemOptionsDialogOpen && selectedItem != null) {
        itemOptionsDialog(
            onDismissRequest = {
                isItemOptionsDialogOpen = false
                selectedItem = null
            },
            currentScreen = currentScreen,
            onAddToPlaylistRequest = { /* TODO addToPlaylist(selectedItem) */ },
            onEditRequest = { isEditDialogOpen = true },
            onDeleteRequest = { /* TODO delete(selectedItem) */ }
        )
    }
    
    if(isEditDialogOpen && selectedItem != null){
        EditItemsDialogs(
            item = selectedItem!!,
            onDismissRequest = {
                isEditDialogOpen = false
                selectedItem = null
            }
        )
    }
}

@Composable
fun <T> ListScreen(
    items: List<T>,
    loading: Boolean,
    currentScreen: String,
    modifier: Modifier = Modifier,
    itemContent: @Composable (T) -> Unit
) {
    if (items.isEmpty()) {
        Column (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Image(
                painter = painterResource(Res.drawable.no_data),
                contentDescription = "Image that depicts that there is no added data to the app",
                modifier = Modifier.size(350.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = when( currentScreen.lowercase() ) {
                    "canciones" -> "No hay canciones registradas"
                    "artistas" -> "No hay artistas registrados"
                    "álbumes" -> "No hay álbumes registrados"
                    "géneros" -> "No hay géneros registrados"
                    else -> "No hay playlists registradas"
                },
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Para añadir, ve al botón",
                    fontSize = 15.sp,
                    style = MaterialTheme.typography.titleLarge,
                    color = if (isSystemInDarkTheme()) Color(0xFFA1A1A1) else Color(0xFF909090),
                    modifier = Modifier
                )

                Image(
                    painter = painterResource(Res.drawable.add),
                    contentDescription = "Add symbol",
                    modifier = Modifier.size(30.dp)
                )
            }
                                                                                                                                                                 

            Spacer(modifier = Modifier.height(50.dp))
        }
    }

    if (loading) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier
        ) {
            items(items) { item ->
                itemContent(item)
            }
        }
    }
}





@Composable
fun SongItem(
    image: DrawableResource?, // Puede ser null para items sin imagen
    title: String,
    subtitle: String,
    onDoubleClick: () -> Unit,
    onOptionClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(onDoubleTap = { onDoubleClick() })
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (image != null) {
                    Image(
                        painter = painterResource(image),
                        contentDescription = null,
                        modifier = Modifier
                            .height(48.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    if (subtitle.isNotEmpty()) {
                        Text(
                            text = subtitle,
                            maxLines = 1,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                    }
                }

                Icon(
                    painter = painterResource(Res.drawable.more),
                    contentDescription = "Opciones",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onOptionClick() }
                )
            }
        }
    }
}

