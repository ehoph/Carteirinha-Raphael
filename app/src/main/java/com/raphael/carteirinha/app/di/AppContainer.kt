package com.raphael.carteirinha.app.di

import com.raphael.carteirinha.core.auth.SessionTokenStore
import com.raphael.carteirinha.feature.aluno.unidadescurriculares.domain.repository.UnidadeCurricularRepository
import com.raphael.carteirinha.feature.login.domain.repository.LoginRepository

interface AppContainer {
    val sessionTokenStore: SessionTokenStore
    val loginRepository: LoginRepository
    val unidadeCurricularRepository: UnidadeCurricularRepository
}
