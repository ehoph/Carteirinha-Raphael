package com.raphael.carteirinha.app.di

import com.raphael.carteirinha.core.auth.SessionTokenStore
import com.raphael.carteirinha.feature.aluno.unidadescurriculares.domain.repository.UnidadeCurricularRepository
import com.raphael.carteirinha.feature.login.domain.repository.LoginRepository
import com.raphael.carteirinha.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import com.raphael.carteirinha.feature.professor.unidadescurriculares.domain.repository.UnidadeCurricularProfessorRepository

interface AppContainer {
    val sessionTokenStore: SessionTokenStore
    val loginRepository: LoginRepository
    val unidadeCurricularRepository: UnidadeCurricularRepository
    val turmaProfessorRepository: TurmaProfessorRepository
    val unidadeCurricularProfessorRepository: UnidadeCurricularProfessorRepository
}
