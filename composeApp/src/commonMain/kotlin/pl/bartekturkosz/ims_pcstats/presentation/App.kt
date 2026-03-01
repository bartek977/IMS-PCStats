package pl.bartekturkosz.ims_pcstats.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinConfiguration
import pl.bartekturkosz.ims_pcstats.di.commonModule
import pl.bartekturkosz.ims_pcstats.di.platformModule
import pl.bartekturkosz.ims_pcstats.presentation.dashboard.Dashboard
import pl.bartekturkosz.ims_pcstats.presentation.dashboard.DashboardScreen
import pl.bartekturkosz.ims_pcstats.presentation.login.Login
import pl.bartekturkosz.ims_pcstats.presentation.login.LoginScreen
import pl.bartekturkosz.ims_pcstats.presentation.theme.IMS_PC_StatsAppTheme

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    KoinMultiplatformApplication(
        config = koinConfiguration {
            modules(commonModule, platformModule)
        }
    ) {
        val navController = rememberNavController()

        IMS_PC_StatsAppTheme {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .safeContentPadding()
                    .fillMaxSize(),
            ) {
                NavHost(navController = navController, startDestination = Login) {
                    composable<Login> { LoginScreen(navController) }
                    composable<Dashboard> { DashboardScreen(navController) }
                }
            }
        }
    }
}