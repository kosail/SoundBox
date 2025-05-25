package com.katsukosail.soundbox

import com.katsukosail.soundbox.Colors.DarkColorScheme
import com.katsukosail.soundbox.Colors.LightColorScheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import soundbox.composeapp.generated.resources.Res
import soundbox.composeapp.generated.resources.logo_long

@Composable
@Preview
fun App() {
    MaterialTheme (
        colorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            var currentScreen by remember { mutableStateOf("Playlists") }
            var searchInput by remember { mutableStateOf("") }

            TopBar(
                searchValue = searchInput,
                searchOnValueChange = { searchInput = it },
                modifier = Modifier
            )

            Row(
                modifier = Modifier.weight(1f)
            ) {
                SideMenu()
                MainContent(currentScreen)
            }
        }
    }
}

@Composable
fun TopBar(
    searchValue: String,
    searchOnValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO : Icon here to open the menu

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
            label = { Text("Buscar") },
            value = searchValue,
            onValueChange = searchOnValueChange,
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
fun SideMenu(
    modifier: Modifier = Modifier
) {
    
}

@Composable
fun MainContent(
    currentScreen: String,
    modifier: Modifier = Modifier
) {

}