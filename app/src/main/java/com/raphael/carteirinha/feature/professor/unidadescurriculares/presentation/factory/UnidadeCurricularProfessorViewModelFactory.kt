package com.raphael.carteirinha.feature.professor.unidadescurriculares.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raphael.carteirinha.feature.professor.unidadescurriculares.domain.repository.UnidadeCurricularProfessorRepository
import com.raphael.carteirinha.feature.professor.unidadescurriculares.presentation.UnidadeCurricularProfessorViewModel

class UnidadeCurricularProfessorViewModelFactory(
    private val repository: UnidadeCurricularProfessorRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnidadeCurricularProfessorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UnidadeCurricularProfessorViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: " + modelClass.name)
    }
}
