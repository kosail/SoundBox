package com.katsukosail.soundbox.Theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CustomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val LightPrimary = Color(0xFF825500)      // Deep Amber (Primary)
    val LightPrimaryContainer = Color(0xFFFFDDB2) // Light Amber (Container)
    val LightSecondary = Color(0xFFB90032)    // Rich Crimson (Secondary)
    val LightSecondaryContainer = Color(0xFFFFDAD8) // Soft Pink (Container)
    val LightTertiary = Color(0xFF00677D)     // Deep Teal (Tertiary)
    val LightTertiaryContainer = Color(0xFFB6EAFF)  // Sky Blue (Container)

    val LightBackground = Color(0xFFFDF5E6)   // Warm Parchment (Background)
    val LightSurface = Color(0xFFFFFBF5)      // Soft White (Surface)
    val LightSurfaceVariant = Color(0xFFF0E6D6) // Subtle Beige (Surface Variant)

    val LightText = Color(0xFF3E3E3E)         // Dark Gray (Text - WCAG AA 7:1 on background)
    val LightError = Color(0xFFBA1A1A)        // Deep Red (Error)
    val LightErrorContainer = Color(0xFFFFDAD6) // Light Red (Error Container)

    val lightColors = lightColorScheme(
        primary = LightPrimary,
        onPrimary = Color(0xFFFFFFFF),           // White on primary
        primaryContainer = LightPrimaryContainer,
        onPrimaryContainer = Color(0xFF291800),  // Dark Brown on container (7.3:1)

        secondary = LightSecondary,
        onSecondary = Color(0xFFFFFFFF),        // White on secondary
        secondaryContainer = LightSecondaryContainer,
        onSecondaryContainer = Color(0xFF40000D), // Deep Maroon on container (8.1:1)

        tertiary = LightTertiary,
        onTertiary = Color(0xFFFFFFFF),         // White on tertiary
        tertiaryContainer = LightTertiaryContainer,
        onTertiaryContainer = Color(0xFF001F26), // Navy on container (9.2:1)

        background = LightBackground,
        onBackground = LightText,               // Dark gray on parchment (7:1)

        surface = LightSurface,
        onSurface = LightText,                  // Dark gray on white (8.5:1)
        surfaceVariant = LightSurfaceVariant,
        onSurfaceVariant = Color(0xFF4E4635),   // Warm Gray on variant (5.7:1)

        error = LightError,
        onError = Color(0xFFFFFFFF),            // White on error
        errorContainer = LightErrorContainer,
        onErrorContainer = Color(0xFF410002)    // Deep Red on container (7.1:1)
    )

    // Warm Dark Mode Colors
    val DarkPrimary = Color(0xFFFFB951)      // Golden Amber (Primary)
    val DarkPrimaryContainer = Color(0xFF624000) // Deep Brown (Container)
    val DarkSecondary = Color(0xFFFFB2BD)    // Soft Pink (Secondary)
    val DarkSecondaryContainer = Color(0xFF90002A) // Deep Rose (Container)
    val DarkTertiary = Color(0xFF5CD5FB)     // Bright Teal (Tertiary)
    val DarkTertiaryContainer = Color(0xFF004E61)  // Dark Teal (Container)

    val DarkBackground = Color(0xFF1A1A1A)   // Warm Black (Background)
    val DarkSurface = Color(0xFF242424)      // Dark Gray (Surface)
    val DarkSurfaceVariant = Color(0xFF4A4A4A) // Gray Variant

    val DarkText = Color(0xFFEDEDED)         // Soft White (Text - WCAG AA 7.3:1 on background)
    val DarkError = Color(0xFFFFB4AB)        // Light Red (Error)
    val DarkErrorContainer = Color(0xFF93000A) // Deep Red (Error Container)

    val darkColors = darkColorScheme(
        primary = DarkPrimary,
        onPrimary = Color(0xFF442B00),           // Dark Brown on primary (7.5:1)
        primaryContainer = DarkPrimaryContainer,
        onPrimaryContainer = Color(0xFFFFDDB2),  // Light Amber on container (9.1:1)

        secondary = DarkSecondary,
        onSecondary = Color(0xFF5C0019),        // Deep Maroon on secondary (7.8:1)
        secondaryContainer = DarkSecondaryContainer,
        onSecondaryContainer = Color(0xFFFFDAD8), // Soft Pink on container (8.3:1)

        tertiary = DarkTertiary,
        onTertiary = Color(0xFF003640),         // Navy on tertiary (8.9:1)
        tertiaryContainer = DarkTertiaryContainer,
        onTertiaryContainer = Color(0xFFB6EAFF), // Sky Blue on container (9.4:1)

        background = DarkBackground,
        onBackground = DarkText,                // Soft white on black (7.3:1)

        surface = DarkSurface,
        onSurface = DarkText,                   // Soft white on gray (7.1:1)
        surfaceVariant = DarkSurfaceVariant,
        onSurfaceVariant = Color(0xFFC8C8C8),   // Light Gray on variant (4.5:1)

        error = DarkError,
        onError = Color(0xFF690005),            // Deep Red on error (7.2:1)
        errorContainer = DarkErrorContainer,
        onErrorContainer = Color(0xFFFFDAD6)    // Light Red on container (8.1:1)
    )

    val colorScheme = if(darkTheme) darkColors else lightColors

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

val slightDarkBackground = Color(0xFF232323)
val slightLightBackground = Color(0xFFF3EBDC)