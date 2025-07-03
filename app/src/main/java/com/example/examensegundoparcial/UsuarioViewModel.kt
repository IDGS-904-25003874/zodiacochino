package com.example.examensegundoparcial

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UsuarioViewModel: ViewModel() {
    private var _usuario = MutableStateFlow(Usuario())
    val usuario: StateFlow<Usuario> = _usuario

    fun updateUsuario(newUsuario: Usuario) {
        _usuario.value = newUsuario
    }

//    fun resetUsuario() {
//        _usuario.value = Usuario()
//    }
}