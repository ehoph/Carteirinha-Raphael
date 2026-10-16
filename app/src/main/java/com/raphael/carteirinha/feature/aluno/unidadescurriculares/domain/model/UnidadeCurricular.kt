package com.raphael.carteirinha.feature.aluno.unidadescurriculares.domain.model

data class UnidadeCurricular(
    val id: String,
    val nome: String,
    val professor: String,
    val nota1: Double,
    val nota2: Double,
    val media: Double,
    val faltas: Int
)