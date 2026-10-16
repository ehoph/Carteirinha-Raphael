package com.raphael.carteirinha.feature.aluno.unidadescurriculares.domain.repository

import com.raphael.carteirinha.feature.aluno.unidadescurriculares.domain.model.UnidadeCurricular

interface UnidadeCurricularRepository {
    suspend fun listar():Result<List<UnidadeCurricular>>
}