package com.katsukosail.soundbox.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.katsukosail.soundbox.Theme.slightDarkBackground
import com.katsukosail.soundbox.Theme.slightLightBackground
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import soundbox.composeapp.generated.resources.*
import soundbox.composeapp.generated.resources.add

@Composable
fun SideMenu(isDrawerOpened: Boolean,
             isDarkTheme: Boolean,
             showSongs: () -> Unit,
             showArtists: () -> Unit,
             showAlbums: () -> Unit,
             showGenres: () -> Unit,
             showPlaylists: () -> Unit,
             add: () -> Unit
) {
    val drawerWidth by animateDpAsState(
        targetValue = if (isDrawerOpened) 250.dp else 70.dp,
        label = "drawerWidth"
    )


    Column(modifier = Modifier.fillMaxHeight().width(drawerWidth).background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally){
        MenuCard("Canciones", Res.drawable.music_note, onClick = {showSongs()}, isDrawerOpened, isDarkTheme)
        MenuCard("Artistas", Res.drawable.artist, onClick = {showArtists()}, isDrawerOpened, isDarkTheme)
        MenuCard("Álbumes", Res.drawable.album, onClick = {showAlbums()}, isDrawerOpened, isDarkTheme)
        MenuCard("Géneros", Res.drawable.genres, onClick = {showGenres()}, isDrawerOpened, isDarkTheme)
        MenuCard("Playlists", Res.drawable.queue_music, onClick = {showPlaylists()}, isDrawerOpened, isDarkTheme)
        Spacer(modifier = Modifier.weight(1f))
        MenuCard("Añadir", Res.drawable.add, onClick = {add()}, isDrawerOpened, isDarkTheme)
    }

}

@Composable
fun MenuCard(
    text: String,
    image: DrawableResource,
    onClick: () -> Unit,
    isDrawerOpened: Boolean,
    isDarkTheme: Boolean
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardColors(
            containerColor = if (isDarkTheme) slightDarkBackground else slightLightBackground,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        modifier = Modifier
            .padding(5.dp)
            .clickable { onClick() }.fillMaxWidth()
    ) {
        var showText by remember { mutableStateOf(false) }

        // Cuando cambie isDrawerOpened, retrasamos el texto
        LaunchedEffect(isDrawerOpened) {
            if (isDrawerOpened) {
                delay(100) // Espera 0.5 segundos
                showText = true
            } else {
                showText = false // Ocultamos inmediatamente si se cierra
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            contentAlignment = Alignment.Center
        ) {
            if (isDrawerOpened) {
                // Modo horizontal
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(image),
                        contentDescription = text,
                        modifier = Modifier.size(50.dp).padding(5.dp)
                    )
                    AnimatedVisibility(visible = showText) {
                        Text(
                            text = text,
                            fontSize = 20.sp,
//                            fontSize = 25.sp,
//                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)
                        )
                    }
                }
            } else {
                // Modo vertical
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(image),
                        contentDescription = text,
                        modifier = Modifier.size(50.dp).padding(5.dp)
                    )

                }
            }
        }

    }
}