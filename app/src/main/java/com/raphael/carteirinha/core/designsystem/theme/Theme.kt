package com.raphael.carteirinha.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFFE30613),
    onPrimary = Color.White,

    primaryContainer = Color(0xFFFFDAD9),
    onPrimaryContainer = Color(0xFF410004),

    secondary = Color(0xFF5F6368),
    onSecondary = Color.White,

    background = Color(0xFFF7F7F9),
    onBackground = Color(0xFF18181B),

    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF18181B),

    surfaceVariant = Color(0xFFF0F0F3),
    onSurfaceVariant = Color(0xFF66666D),

    outline = Color(0xFFD4D4D8),
    outlineVariant = Color(0xFFE4E4E7),

    error = Color(0xFFBA1A1A),
    onError = Color.White
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFFFB3B3),
    onPrimary = Color(0xFF680006),

    primaryContainer = Color(0xFF93000A),
    onPrimaryContainer = Color(0xFFFFDAD9),

    secondary = Color(0xFFC7C7CE),
    onSecondary = Color(0xFF303034),

    background = Color(0xFF111113),
    onBackground = Color(0xFFE5E5E7),

    surface = Color(0xFF19191C),
    onSurface = Color(0xFFE5E5E7),

    surfaceVariant = Color(0xFF252529),
    onSurfaceVariant = Color(0xFFC7C7CE),

    outline = Color(0xFF8E8E96),

    error = Color(0xFFFFB4AB)
)

@Composable
fun CarteirinhaDigital2DEVEST_BTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content
    )
}