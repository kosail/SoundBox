package com.katsukosail.soundbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import soundbox.composeapp.generated.resources.Res
import soundbox.composeapp.generated.resources.artists
import soundbox.composeapp.generated.resources.logo_long
import soundbox.composeapp.generated.resources.playlists
import soundbox.composeapp.generated.resources.songs

@Composable
@Preview
fun App() {
    CustomTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            var currentScreen by remember { mutableStateOf("Playlists") }
            var searchInput by remember { mutableStateOf("") }

            var isDrawerOpened by remember {mutableStateOf(false)}

            TopBar(
                searchValue = searchInput,
                searchOnValueChange = { searchInput = it },
                modifier = Modifier,
                onOpenDrawer = { isDrawerOpened = !isDrawerOpened }
            )

            Row(
                modifier = Modifier.weight(1f)
            ) {
                SideMenu(isDrawerOpened,
                    showSongs = {currentScreen = "Canciones"},
                    showArtists = {currentScreen = "Artistas"},
                    showAlbums = {currentScreen = "Álbumes"},
                    showPlaylists = {currentScreen = "Playlists"})


                MainContent(currentScreen)
            }
        }
    }
}

@Composable
fun TopBar(
    searchValue: String,
    searchOnValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onOpenDrawer: () -> Unit
) {
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Rounded.Menu,
            contentDescription = null,
            tint = Color(0xFF876545),
            modifier = Modifier
                .size(40.dp)
                .weight(0.3f)
                .clickable(onClick = onOpenDrawer)
        )

        Image(
            painter = painterResource(Res.drawable.logo_long),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .weight(0.5f)
        )

        TextField(
            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null
                )
            },
            placeholder = { Text("Buscar") },
            value = searchValue,
            onValueChange = searchOnValueChange,
            shape = RoundedCornerShape(25.dp),
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                focusedIndicatorColor = Color.Transparent,    // remove underline (focused)
                unfocusedIndicatorColor = Color.Transparent,  // remove underline (unfocused)
                disabledIndicatorColor = Color.Transparent,   // remove underline (disabled)
            ),
            modifier = Modifier
                .weight(1f)
        )

        Icon(
            imageVector = Icons.Rounded.Settings,
            contentDescription = null,
            tint = Color(0xFF876545),
            modifier = Modifier
                .size(40.dp)
                .weight(0.3f)
        )
    }
}

@Composable
fun SideMenu(isDrawerOpened: Boolean,
             showSongs: () -> Unit,
             showArtists: () -> Unit,
             showAlbums: () -> Unit,
             showPlaylists: () -> Unit,
             modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally){
        MenuCard("Canciones", Res.drawable.songs, onClick = {showSongs()}, isDrawerOpened)
        MenuCard("Artistas", Res.drawable.artists, onClick = {showArtists()}, isDrawerOpened)
        MenuCard("Álbumes", Res.drawable.songs, onClick = {showAlbums()}, isDrawerOpened)
        MenuCard("Playlists", Res.drawable.playlists, onClick = {showPlaylists()}, isDrawerOpened)
    }

}

@Composable
fun MenuCard(text: String, image: DrawableResource, onClick: () -> Unit, isDrawerOpened: Boolean) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardColors(
            containerColor = Color.Red,
            contentColor = Color.White,
            disabledContentColor = Color.White,
            disabledContainerColor = Color.Red
        ),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        if (isDrawerOpened) {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(image), contentDescription = text, modifier = Modifier.size(30.dp))
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = text,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)
                )
            }
        } else {
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(image), contentDescription = text, modifier = Modifier.size(30.dp))
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = text,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)
                )
            }
        }

    }
}


@Composable
fun MainContent(
    currentScreen: String,
    modifier: Modifier = Modifier
) {

}