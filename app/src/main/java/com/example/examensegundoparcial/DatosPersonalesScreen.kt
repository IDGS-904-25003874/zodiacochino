package com.example.examensegundoparcial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun datosPersonalesScreen(
    usuario: Usuario,
    onUsuarioChange: (Usuario) -> Unit,
    onSiguiente: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Formulario de Registro",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = usuario.nombre,
            onValueChange = { onUsuarioChange(usuario.copy(nombre = it)) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = usuario.apellidoPaterno,
            onValueChange = { onUsuarioChange(usuario.copy(apellidoPaterno = it)) },
            label = { Text("Apellido Paterno") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = usuario.apellidoMaterno,
            onValueChange = { onUsuarioChange(usuario.copy(apellidoMaterno = it)) },
            label = { Text("Apellido Materno") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Fecha de Nacimiento",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = usuario.dia,
                onValueChange = { onUsuarioChange(usuario.copy(dia = it)) },
                label = { Text("Día") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = usuario.mes,
                onValueChange = { onUsuarioChange(usuario.copy(mes = it)) },
                label = { Text("Mes") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = usuario.año,
                onValueChange = { onUsuarioChange(usuario.copy(año = it)) },
                label = { Text("Año") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }

        Text(
            text = "Sexo",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = usuario.sexo == "Masculino",
                        onClick = { onUsuarioChange(usuario.copy(sexo = "Masculino")) }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = usuario.sexo == "Masculino",
                    onClick = { onUsuarioChange(usuario.copy(sexo = "Masculino")) }
                )
                Text(text = "Masculino")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = usuario.sexo == "Femenino",
                        onClick = { onUsuarioChange(usuario.copy(sexo = "Femenino")) }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = usuario.sexo == "Femenino",
                    onClick = { onUsuarioChange(usuario.copy(sexo = "Femenino")) }
                )
                Text(text = "Femenino")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    onUsuarioChange(Usuario())
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Limpiar")
            }

            Button(
                onClick = onSiguiente,
                modifier = Modifier.weight(1f),
                enabled = usuario.nombre.isNotEmpty() &&
                        usuario.apellidoPaterno.isNotEmpty() &&
                        usuario.dia.isNotEmpty() &&
                        usuario.mes.isNotEmpty() &&
                        usuario.año.isNotEmpty() &&
                        usuario.sexo.isNotEmpty()
            ) {
                Text("Siguiente")
            }
        }
    }
}