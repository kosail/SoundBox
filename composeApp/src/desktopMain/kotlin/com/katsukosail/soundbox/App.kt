package com.katsukosail.soundbox

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.rememberDialogState
import com.katsukosail.soundbox.Theme.CustomTheme
import com.katsukosail.soundbox.Theme.rememberAppThemeState
import com.katsukosail.soundbox.Theme.slightDarkBackground
import com.katsukosail.soundbox.Theme.slightLightBackground
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import soundbox.composeapp.generated.resources.Res
import soundbox.composeapp.generated.resources.artists
import soundbox.composeapp.generated.resources.logo_long
import soundbox.composeapp.generated.resources.playlists
import soundbox.composeapp.generated.resources.songs
import soundbox.composeapp.generated.resources.github

@Composable
@Preview
fun App() {
    val appThemeState = rememberAppThemeState()

    CustomTheme(darkTheme = appThemeState.isDarkTheme) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            var currentScreen by remember { mutableStateOf("Playlists") }
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
                    showPlaylists = { currentScreen = "Playlists" }
                )

                MainContent(currentScreen)

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

@Composable
fun TopBar(
    searchValue: String,
    searchOnValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onOpenDrawer: () -> Unit,
    onOpenSettings: () -> Unit
) {
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {

        Spacer(
            modifier = Modifier.width(25.dp)
        )

        IconButton(
            onClick = onOpenDrawer,
            modifier = Modifier
        ) {
            Icon(
                imageVector = Icons.Rounded.Menu,
                contentDescription = "Menu button",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(35.dp)
            )
        }

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Image(
            painter = painterResource(Res.drawable.logo_long),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .weight(0.5f)
        )

        Spacer( modifier = Modifier.width(10.dp) )

        TextField(
            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 10.dp)
                )
            },
            placeholder = {
                Text("Buscar", modifier = Modifier.padding(start = 10.dp))
            },
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

        Spacer(
            modifier = Modifier.width(25.dp)
        )

        IconButton(
            onClick = onOpenSettings,
            modifier = Modifier
        ) {
            Icon(
                imageVector = Icons.Rounded.Settings,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(30.dp)
            )
        }

        Spacer(
            modifier = Modifier.width(20.dp)
        )
    }
}

@Composable
fun SideMenu(isDrawerOpened: Boolean,
             isDarkTheme: Boolean,
             showSongs: () -> Unit,
             showArtists: () -> Unit,
             showAlbums: () -> Unit,
             showPlaylists: () -> Unit,
             modifier: Modifier = Modifier
) {
    val drawerWidth by animateDpAsState(
        targetValue = if (isDrawerOpened) 250.dp else 70.dp,
        label = "drawerWidth"
    )


    Column(modifier = Modifier.fillMaxHeight().width(drawerWidth).background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally){
        MenuCard("Canciones", Res.drawable.songs, onClick = {showSongs()}, isDrawerOpened, isDarkTheme)
        MenuCard("Artistas", Res.drawable.artists, onClick = {showArtists()}, isDrawerOpened, isDarkTheme)
        MenuCard("Álbumes", Res.drawable.songs, onClick = {showAlbums()}, isDrawerOpened, isDarkTheme)
        MenuCard("Playlists", Res.drawable.playlists, onClick = {showPlaylists()}, isDrawerOpened, isDarkTheme)
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
        // ! Removido para quitar la sombra de elevación, y que así no sea el boton transparente y la sombra negra alrededor
//        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardColors(
            // ! Removido en favor de botones transparentes
//            containerColor = MaterialTheme.colorScheme.primaryContainer,
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


@Composable
fun SettingsDialog(
    onCloseRequest: () -> Unit,
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    DialogWindow(
        onCloseRequest = onCloseRequest,
        state = rememberDialogState(),
        visible = true,
        title = "Settings",
        undecorated = true,
        icon = rememberVectorPainter(Icons.Rounded.Settings),
        resizable = false,
        content = {
            Surface (
                shape = RoundedCornerShape(16.dp),
                tonalElevation = 8.dp,
                color = MaterialTheme.colorScheme.background,
                modifier = modifier
                    .fillMaxSize()
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

                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "Configuración",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.DarkMode,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(25.dp)
                                    .weight(0.3f)
                            )

                            Text(
                                text = "Modo oscuro",
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .weight(1f)
                            )

                            Switch(
                                checked = darkTheme,
                                onCheckedChange = { onThemeChange(!darkTheme) },
                                modifier = Modifier.weight(0.3f)
                            )
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                    }
                    
                }
            }
        },
    )
}

@Composable
fun MainContent(
    currentScreen: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        Text(
            text = "Ejemplo de pantallasasasas",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(30.dp)
        )
    }
}