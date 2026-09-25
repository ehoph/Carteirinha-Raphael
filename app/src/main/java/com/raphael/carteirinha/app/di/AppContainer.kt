package com.raphael.carteirinha.app.di

import com.raphael.carteirinha.core.auth.AuthTokenStore
import com.raphael.carteirinha.feature.login.data.repository.LoginRepository
import com.raphael.carteirinha.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository

interface AppContainer {

    val loginRepository: LoginRepository

    val unidadeCurricularRepository: UnidadeCurricularRepository

    val authTokenStore: AuthTokenStore
}