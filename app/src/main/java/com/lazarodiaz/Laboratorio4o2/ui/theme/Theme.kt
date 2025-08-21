package com.lazarodiaz.Laboratorio4o2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF7E57C2),
    secondary = Color(0xFF26C6DA),
    tertiary = Color(0xFFFF7043),
    background = Color(0xFFF8F6FA),
    surface = Color.White
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFB39DDB),
    secondary = Color(0xFF4DD0E1),
    tertiary = Color(0xFFFF8A65),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E)
)

@Composable
fun ThemeMyApplication(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
