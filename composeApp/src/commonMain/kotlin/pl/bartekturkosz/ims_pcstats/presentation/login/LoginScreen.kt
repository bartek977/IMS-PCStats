package pl.bartekturkosz.ims_pcstats.presentation.login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel
import pl.bartekturkosz.ims_pcstats.presentation.AppScreen

@Serializable
data object Login

@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel = koinViewModel<LoginViewModel>()
    val url by viewModel.url.collectAsState()
    val login by viewModel.login.collectAsState()
    val password by viewModel.password.collectAsState()

    AppScreen(
        title = "Haier Stats",
    ) {
        Text("Aby zacząć korzystać z aplikacji, zaloguj się używając danych z HiveMQ")
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = url,
            onValueChange = { viewModel.url.value = it },
            label = { Text("URL") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = login,
            onValueChange = { viewModel.login.value = it },
            label = { Text("Login") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.password.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = viewModel::onLoginClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Text("Login")
        }
    }
}