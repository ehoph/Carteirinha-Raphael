package com.raphael.carteirinha.feature.professor.turmas.domain.repository

import com.raphael.carteirinha.feature.professor.turmas.domain.model.TurmaProfessor

interface TurmaProfessorRepository {
    suspend fun listar(): Result<List<TurmaProfessor>>
}
