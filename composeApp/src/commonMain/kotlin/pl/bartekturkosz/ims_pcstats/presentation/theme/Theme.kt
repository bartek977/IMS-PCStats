package pl.bartekturkosz.ims_pcstats.presentation.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun IMS_PC_StatsAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppLightColorScheme,
        typography = AppTypography,
        content = content
    )
}