package pl.bartekturkosz.ims_pcstats.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppScreen(
    title: String,
    snackBarHostState: SnackbarHostState? = null,
    content: @Composable () -> Unit
) {
    Scaffold(
        snackbarHost = { snackBarHostState?.let { SnackbarHost(it) } }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            HorizontalDivider()
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            HorizontalDivider()
            content()
        }
    }
}