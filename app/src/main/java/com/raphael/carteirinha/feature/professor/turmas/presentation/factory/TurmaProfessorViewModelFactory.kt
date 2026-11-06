package com.raphael.carteirinha.feature.professor.turmas.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raphael.carteirinha.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import com.raphael.carteirinha.feature.professor.turmas.presentation.TurmaProfessorViewModel

class TurmaProfessorViewModelFactory(
    private val repository: TurmaProfessorRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TurmaProfessorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TurmaProfessorViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: " + modelClass.name)
    }
}
