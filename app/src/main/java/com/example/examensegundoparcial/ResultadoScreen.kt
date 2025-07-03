package com.example.examensegundoparcial

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.util.*

fun obtenerSignoZodiacoChino(año: Int): Pair<String, Int> {
    val signos = listOf(
        "Rata", "Buey", "Tigre", "Conejo", "Dragón", "Serpiente",
        "Caballo", "Cabra", "Mono", "Gallo", "Perro", "Cerdo"
    )
    val imagenes = listOf(
        R.drawable.rata, R.drawable.buey, R.drawable.tigre, R.drawable.conejo,
        R.drawable.dragon, R.drawable.serpiente, R.drawable.caballo, R.drawable.cabra,
        R.drawable.mono, R.drawable.gallo, R.drawable.perro, R.drawable.cerdo
    )
    val indice = (año - 1900) % 12
    return Pair(signos[indice], imagenes[indice])
}

fun guardarEncuestaEnFirebase(usuario: Usuario, onSucces: ()->Unit, onError: (Exception)->Unit) {
    val db = Firebase.firestore
    db.collection("encuestas").add(usuario).addOnSuccessListener {
        onSucces()
    }.addOnFailureListener {
        onError(it)
    }
}


@Composable
fun resultadoScreen(usuario: Usuario) {
    var enviado by remember { mutableStateOf(false) }
    val añoNacimiento = usuario.año.toIntOrNull() ?: 2000
    val edad = Calendar.getInstance().get(Calendar.YEAR) - añoNacimiento
    val (signo, imagenRes) = obtenerSignoZodiacoChino(añoNacimiento)

    LaunchedEffect(Unit) {
        if (!enviado) {
            guardarEncuestaEnFirebase(usuario, onSucces = {
                enviado = true
            }, onError = {
                enviado = false
                println("Error al guardar la encuesta: ${it.message}")
            })
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
            .padding(24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                buildAnnotatedString {
                    append("Hola ")
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
                    ) {
                        append("${usuario.nombre} ${usuario.apellidoPaterno} ${usuario.apellidoMaterno}")
                    }
                },
                fontSize = 20.sp
            )

            Text("Tienes ${edad} años y tu signo zodiacal")

            Image(
                painter = painterResource(id = imagenRes),
                contentDescription = signo,
                modifier = Modifier.size(100.dp)
            )

            Text("Es $signo")

            Text(
                "Calificación ${usuario.calificacion}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}