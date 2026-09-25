package com.raphael.carteirinha.feature.login.domain.model

data class UsuarioLogado (
    val id: String,
    val nome: String,
    val curso: String,
    val turma: String,
    val token: String
)