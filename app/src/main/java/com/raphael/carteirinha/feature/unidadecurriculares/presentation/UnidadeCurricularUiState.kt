package com.raphael.carteirinha.feature.unidadecurriculares.presentation

import com.raphael.carteirinha.feature.unidadecurriculares.domain.model.UnidadeCurricular

data class UnidadeCurricularUiState(
    val listaUnidadesCurriculares: List<UnidadeCurricular> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
}