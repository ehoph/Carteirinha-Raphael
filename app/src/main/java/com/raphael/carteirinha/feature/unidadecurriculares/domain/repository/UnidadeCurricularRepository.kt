package com.raphael.carteirinha.feature.unidadecurriculares.domain.repository

import com.raphael.carteirinha.feature.unidadecurriculares.domain.model.UnidadeCurricular

interface UnidadeCurricularRepository {
    suspend fun listarUnidadesCurriculares(): Result<List<UnidadeCurricular>>
}