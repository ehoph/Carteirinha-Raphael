package com.raphael.carteirinha.feature.professor.turmas.presentation

import com.raphael.carteirinha.feature.professor.turmas.domain.model.TurmaProfessor

data class TurmaProfessorUiState(
    val isLoading: Boolean = false,
    val turmas: List<TurmaProfessor> = emptyList(),
    val errorMessage: String? = null
)
