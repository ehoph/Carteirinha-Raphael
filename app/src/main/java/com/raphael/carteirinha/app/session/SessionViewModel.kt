package com.raphael.carteirinha.app.session

import androidx.lifecycle.ViewModel
import com.raphael.carteirinha.feature.login.domain.model.UsuarioLogado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SessionViewModel: ViewModel() {
    private val _usuarioLogado = MutableStateFlow<UsuarioLogado?>(null)
    val usuarioLogado: StateFlow<UsuarioLogado?> = _usuarioLogado.asStateFlow()

    fun setUsuarioLogado(usuario: UsuarioLogado){
        _usuarioLogado.value = usuario
    }
    fun limparSession(){
        _usuarioLogado.value = null
    }
}