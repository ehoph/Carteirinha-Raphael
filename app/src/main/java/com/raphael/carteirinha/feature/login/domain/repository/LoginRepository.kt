package com.raphael.carteirinha.feature.login.domain.repository

import com.raphael.carteirinha.feature.login.domain.model.UsuarioLogado

interface LoginRepository {
    suspend fun login(login: String, senha: String): Result<UsuarioLogado>
}