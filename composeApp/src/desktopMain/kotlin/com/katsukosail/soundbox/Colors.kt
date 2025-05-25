package com.katsukosail.soundbox

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CustomTheme(content: @Composable () -> Unit) {
    val GreenPrimary = Color(0xFFC1BF91)    // Primary - Green pastel
    val PinkSecondary = Color(0xFFFBB996)   // Secondary - Soft pink
    val YellowTertiary = Color(0xFFFEDFAD)  // Tertiary - Soft yellow

    val BlueBackground = Color(0xFFBDCFCC)  // Background
    val BlueSurface = Color(0xFFEAF2F1)     // Lighter variant for surfaces

    val BrownText = Color(0xFF876545)       // OnPrimary, OnSurface
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)

    val lightColors = lightColorScheme(
        primary = GreenPrimary,
        onPrimary = BrownText,

        secondary = PinkSecondary,
        onSecondary = BrownText,

        tertiary = YellowTertiary,
        onTertiary = BrownText,

        background = BlueBackground,
        onBackground = BrownText,

        surface = BlueSurface,
        onSurface = BrownText,

        error = Color(0xFFE57373),      // Optional: soft red
        onError = White,
    )

    val GreenPrimaryDark = Color(0xFF909167)
    val PinkSecondaryDark = Color(0xFFD89C7F)
    val YellowTertiaryDark = Color(0xFFD7B97E)

    val BlueBackgroundDark = Color(0xFF263230)
    val BlueSurfaceDark = Color(0xFF3A4A47)

    val BrownTextDark = Color(0xFFF2E9D7)

    val darkColors = darkColorScheme(
        primary = GreenPrimaryDark,
        onPrimary = BrownTextDark,

        secondary = PinkSecondaryDark,
        onSecondary = BrownTextDark,

        tertiary = YellowTertiaryDark,
        onTertiary = BrownTextDark,

        background = BlueBackgroundDark,
        onBackground = BrownTextDark,

        surface = BlueSurfaceDark,
        onSurface = BrownTextDark,

        error = Color(0xFFEF9A9A),
        onError = Color(0xFF1C1C1C),
    )
    val colorScheme = if(isSystemInDarkTheme()) darkColors else lightColors

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )

}
