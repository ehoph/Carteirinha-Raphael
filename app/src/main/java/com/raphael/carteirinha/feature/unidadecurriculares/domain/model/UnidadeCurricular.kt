package com.raphael.carteirinha.feature.unidadecurriculares.domain.model

data class UnidadeCurricular(
    val id: String,
    val nome: String,
    val professor: String,
    val nota1: Double,
    val nota2: Double,
    val media: Double,
    val faltas: Int
)