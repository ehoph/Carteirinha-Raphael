package com.raphael.carteirinha.feature.login.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val id: String,
    val nome: String,
    val curso: String,
    val turma: String,
    val token: String
)