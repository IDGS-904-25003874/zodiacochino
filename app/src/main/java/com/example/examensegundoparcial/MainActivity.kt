package com.example.examensegundoparcial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examensegundoparcial.ui.theme.ExamenSegundoParcialTheme

class MainActivity : ComponentActivity() {
    private val usuarioViewModel: UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val viewModel: UsuarioViewModel = viewModel()
            val usuario by usuarioViewModel.usuario.collectAsState()

            NavHost(navController = navController, startDestination = "datosPersonales") {
                composable("datosPersonales") {
                    datosPersonalesScreen(
                        usuario = usuario,
                        onUsuarioChange = { usuarioViewModel.updateUsuario(it) },
                        onSiguiente = { navController.navigate("calificacion") }
                    )
                }
                composable("calificacion") {
                    examenScreen(
                        onTerminar = { calificacion ->
                            usuarioViewModel.updateUsuario(
                                usuario.copy(calificacion = calificacion)
                            )
                            navController.navigate("resultado")
                        }
                    )
                }
                composable("resultado") {
                    resultadoScreen(usuario = usuario)
                }

            }
        }
    }
}