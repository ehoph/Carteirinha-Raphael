package com.raphael.carteirinha.feature.login.data.repository

import com.raphael.carteirinha.feature.login.domain.model.UsuarioLogado

interface LoginRepository {
    suspend fun login( usuario:String, senha:String): Result<UsuarioLogado>
}