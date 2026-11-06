package com.raphael.carteirinha.feature.professor.turmas.data.remote.dto

import com.raphael.carteirinha.feature.professor.turmas.domain.model.TurmaProfessor
import kotlinx.serialization.Serializable

@Serializable
data class TurmaProfessorDto(
    val id: String,
    val codigo: String,
    val curso: String,
    val semestre: String,
    val quantidadeAlunos: Int,
    val diasSemana: List<String>,
    val horarioInicio: String,
    val horarioFim: String
) {
    fun toDomain(): TurmaProfessor {
        return TurmaProfessor(
            id = id,
            codigo = codigo,
            curso = curso,
            semestre = semestre,
            quantidadeAlunos = quantidadeAlunos,
            diasSemana = diasSemana,
            horarioInicio = horarioInicio,
            horarioFim = horarioFim
        )
    }
}
