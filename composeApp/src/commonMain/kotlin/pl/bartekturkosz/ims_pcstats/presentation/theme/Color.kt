package pl.bartekturkosz.ims_pcstats.presentation.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// PRIMARY – energia / efektywność
val md_theme_light_primary = Color(0xFF2E7D32)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFB7E1C1)
val md_theme_light_onPrimaryContainer = Color(0xFF0A3816)

// SECONDARY – technika / dane
val md_theme_light_secondary = Color(0xFF0277BD)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFCFE9F9)
val md_theme_light_onSecondaryContainer = Color(0xFF001E30)

// TERTIARY – wydajność / przepływ
val md_theme_light_tertiary = Color(0xFF00897B)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFB2DFDB)
val md_theme_light_onTertiaryContainer = Color(0xFF00201B)

// ERROR
val md_theme_light_error = Color(0xFFD32F2F)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onErrorContainer = Color(0xFF410002)

// BACKGROUND

val md_theme_light_background = Color(0xFFF1F5F9)
val md_theme_light_onBackground = Color(0xFF1A1C1B)

// SURFACE – NIE czysta biel
val md_theme_light_surface = Color(0xFFF1F6F3)
val md_theme_light_onSurface = Color(0xFF1A1C1B)

// ⭐ WYRAZISTE CARD
val md_theme_light_surfaceContainerLowest = Color(0xFFFFFFFF)
val md_theme_light_surfaceContainerLow = Color(0xFFE3F2EA)   // << Card
val md_theme_light_surfaceContainer = Color(0xFFD7EDE3)
val md_theme_light_surfaceContainerHigh = Color(0xFFCCE6DA)
val md_theme_light_surfaceContainerHighest = Color(0xFFC0DFC9)

// VARIANTS / OUTLINE
val md_theme_light_surfaceVariant = Color(0xFFDCE5DF)
val md_theme_light_onSurfaceVariant = Color(0xFF404844)
val md_theme_light_outline = Color(0xFF6F7973)
val md_theme_light_outlineVariant = Color(0xFFBECAC3)

// OTHER
val md_theme_light_inverseSurface = Color(0xFF2E322F)
val md_theme_light_inverseOnSurface = Color(0xFFF0F1F0)
val md_theme_light_inversePrimary = Color(0xFF81C784)
val md_theme_light_scrim = Color(0xFF000000)

val AppLightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,

    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,

    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,

    error = md_theme_light_error,
    onError = md_theme_light_onError,
    errorContainer = md_theme_light_errorContainer,
    onErrorContainer = md_theme_light_onErrorContainer,

    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,

    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,

    outline = md_theme_light_outline,
    outlineVariant = md_theme_light_outlineVariant,

    scrim = md_theme_light_scrim,

    inverseSurface = md_theme_light_inverseSurface,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceContainerLowest = md_theme_light_surfaceContainerLowest,
    surfaceContainerLow = md_theme_light_surfaceContainerLow,
    surfaceContainer = md_theme_light_surfaceContainer,
    surfaceContainerHigh = md_theme_light_surfaceContainerHigh,
    surfaceContainerHighest = md_theme_light_surfaceContainerHighest,
)