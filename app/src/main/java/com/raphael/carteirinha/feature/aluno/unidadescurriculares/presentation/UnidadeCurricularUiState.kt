package com.raphael.carteirinha.feature.aluno.unidadescurriculares.presentation

import com.raphael.carteirinha.feature.aluno.unidadescurriculares.domain.model.UnidadeCurricular

data class UnidadeCurricularUiState(
    val isLoading: Boolean = false,
    val unidades: List<UnidadeCurricular> = emptyList(),
    val errorMessage: String? = null
)