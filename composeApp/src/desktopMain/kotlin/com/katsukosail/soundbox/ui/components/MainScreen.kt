package com.katsukosail.soundbox.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.katsukosail.soundbox.model.Song
import com.katsukosail.soundbox.model.songs
import com.katsukosail.soundbox.model.artists
import com.katsukosail.soundbox.model.albums
import com.katsukosail.soundbox.model.genres
import com.katsukosail.soundbox.model.playlists
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import soundbox.composeapp.generated.resources.Res
import soundbox.composeapp.generated.resources.more

@Composable
fun MainScreen(
    currentScreen: String,
    modifier: Modifier = Modifier
) {
    var isPlayingDialogOpen by remember { mutableStateOf(false) }
    var selectedSong by remember { mutableStateOf<Song?>(null) }

    Surface(
        modifier = modifier
            .fillMaxSize()
    ){
        when (currentScreen) {
            "Canciones" -> ListScreen(
                items = songs,
                loading = false,
                itemContent = { song ->
                    SongItem(
                        image = song.album.image,
                        title = song.name,
                        subtitle = song.album.artist.name,
                        onDoubleClick = {
                            selectedSong = song
                            isPlayingDialogOpen = true
                        }
                    )
                }
            )

            "Álbumes" -> ListScreen(
                items = albums,
                loading = false,
                itemContent = { album ->
                    SongItem(
                        image = album.image,
                        title = album.name,
                        subtitle = "${album.date} • ${album.artist.name}",
                        onDoubleClick = { /* abrir detalles */ }
                    )
                }
            )

            "Artistas" -> ListScreen(
                items = artists,
                loading = false,
                itemContent = { artist ->
                    SongItem(
                        image = artist.image,
                        title = artist.name,
                        subtitle = "",
                        onDoubleClick = { /* abrir artista */ }
                    )
                }
            )

            "Géneros" -> ListScreen(
                items = genres,
                loading = false,
                itemContent = { genre ->
                    SongItem(
                        image = null,
                        title = genre.name,
                        subtitle = "",
                        onDoubleClick = { /* abrir género */ }
                    )
                }
            )

            "Playlists" -> ListScreen(
                items = playlists,
                loading = false,
                itemContent = { playlist ->
                    SongItem(
                        image = null,
                        title = playlist.name,
                        subtitle = playlist.description,
                        onDoubleClick = { /* abrir playlist */ }
                    )
                }
            )
        }
    }
    if (isPlayingDialogOpen && selectedSong != null) {
        PlaySongDialog(
            song = selectedSong!!,
            onCloseRequest = {
                isPlayingDialogOpen = false
                selectedSong = null
            }
        )
    }
}

@Composable
fun <T> ListScreen(
    items: List<T>,
    loading: Boolean,
    modifier: Modifier = Modifier,
    itemContent: @Composable (T) -> Unit
) {
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
    onDoubleClick: () -> Unit
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
                        .clickable { /* TODO: Opciones */ }
                )
            }
        }
    }
}
