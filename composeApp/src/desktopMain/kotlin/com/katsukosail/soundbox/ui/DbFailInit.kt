package com.katsukosail.soundbox.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DbFailInit(
    exitApp: () -> Unit
) {
    Dialog(
        onDismissRequest = exitApp,
        properties = DialogProperties(usePlatformDefaultWidth = false, scrimColor = Color.Black.copy(0.9f)),
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Aviso",
                letterSpacing = 4.sp,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "La aplicación no puede conectar con la base de datos debido a un error interno. " +
                        "Por ello, no se puede continuar",
                color = Color.White
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = exitApp,
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF660000),
                ),
                modifier = Modifier
            ) { Text("Salir") }
        }
    }
}