package com.katsukosail.soundbox

import com.katsukosail.soundbox.Colors.DarkColorScheme
import com.katsukosail.soundbox.Colors.LightColorScheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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

            var isDrawerOpened by remember {mutableStateOf(false)}

            TopBar(
                searchValue = searchInput,
                searchOnValueChange = { searchInput = it },
                modifier = Modifier,
                onOpenDrawer = {!isDrawerOpened}
            )

            Row(
                modifier = Modifier.weight(1f)
            ) {
                SideMenu(isDrawerOpened)
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
            colors = TextFieldDefaults.colors(
                focusedTextColor = PrimaryBlack,
                unfocusedTextColor = PrimaryBlack,
                focusedPlaceholderColor = Beige,
                unfocusedPlaceholderColor = Beige,
                focusedContainerColor = LightBeige,
                unfocusedContainerColor = LightBeige
            ),
            maxLines = 1,
            singleLine = true,
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
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.fillMaxHeight()){


    }
    
}

@Composable
fun MenuCard(text: String, onClick: () -> Unit) {
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
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)
        )
    }
}

@Composable
fun MainContent(
    currentScreen: String,
    modifier: Modifier = Modifier
) {

}