package com.example.examensegundoparcial

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Pregunta(
    val texto: String,
    val opciones: List<String>,
    val respuestaCorrecta: Int
)

@Composable
fun examenScreen(
    onTerminar: (Int) -> Unit
) {
    val preguntas = listOf(
        Pregunta("¿Capital de Francia?", listOf("Roma", "Madrid", "París", "Berlín"), 2),
        Pregunta("¿2 + 2?", listOf("3", "4", "5", "6"), 1),
        Pregunta("¿Color del cielo?", listOf("Rojo", "Azul", "Verde", "Amarillo"), 1),
        Pregunta("¿Día después del lunes?", listOf("Domingo", "Martes", "Viernes", "Sábado"), 1),
        Pregunta("¿Animal que maúlla?", listOf("Perro", "Vaca", "Gato", "Caballo"), 2),
        Pregunta("¿Planeta rojo?", listOf("Tierra", "Venus", "Marte", "Júpiter"), 2)
    )

    val respuestasUsuario = remember { mutableStateListOf(-1, -1, -1, -1, -1, -1) }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            itemsIndexed(preguntas) { index, pregunta ->
                Column {
                    Text(text = pregunta.texto)
                    pregunta.opciones.forEachIndexed { i, opcion ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            RadioButton(
                                selected = respuestasUsuario[index] == i,
                                onClick = { respuestasUsuario[index] = i }
                            )
                            Text(text = opcion)
                        }
                    }
                }
            }
        }

        Button(
            onClick = {
                val correctas = preguntas.indices.count { i ->
                    respuestasUsuario[i] == preguntas[i].respuestaCorrecta
                }
                val calificacion = (correctas * 10) / preguntas.size
                onTerminar(calificacion)
            },
            enabled = respuestasUsuario.none { it == -1 },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Finalizar examen")
        }
    }
}