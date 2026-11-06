package com.raphael.carteirinha.feature.professor.unidadescurriculares.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raphael.carteirinha.feature.professor.unidadescurriculares.domain.repository.UnidadeCurricularProfessorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UnidadeCurricularProfessorViewModel(
    private val repository: UnidadeCurricularProfessorRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UnidadeCurricularProfessorUiState())
    val uiState: StateFlow<UnidadeCurricularProfessorUiState> = _uiState.asStateFlow()

    fun carregar() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            repository.listar()
                .onSuccess { unidades ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            unidades = unidades,
                            errorMessage = null
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message ?: "Erro ao carregar unidades curriculares."
                        )
                    }
                }
        }
    }
}
