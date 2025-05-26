package com.katsukosail.soundbox.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.katsukosail.soundbox.Theme.slightDarkBackground
import com.katsukosail.soundbox.Theme.slightLightBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsDialog(
    onCloseRequest: () -> Unit,
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    BasicAlertDialog(
        onDismissRequest = onCloseRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.width(500.dp).height(300.dp)
    ) {
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
    }
}