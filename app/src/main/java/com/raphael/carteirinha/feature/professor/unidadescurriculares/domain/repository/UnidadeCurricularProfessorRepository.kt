package com.raphael.carteirinha.feature.professor.unidadescurriculares.domain.repository

import com.raphael.carteirinha.feature.professor.unidadescurriculares.domain.model.UnidadeCurricularProfessor

interface UnidadeCurricularProfessorRepository {
    suspend fun listar(): Result<List<UnidadeCurricularProfessor>>
}
