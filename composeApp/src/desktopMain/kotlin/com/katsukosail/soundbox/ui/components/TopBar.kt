package com.katsukosail.soundbox.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import soundbox.composeapp.generated.resources.Res
import soundbox.composeapp.generated.resources.logo_long

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
