package com.raphael.carteirinha.feature.professor.unidadescurriculares.data.repository

import com.raphael.carteirinha.feature.professor.unidadescurriculares.data.remote.service.UnidadeCurricularProfessorApi
import com.raphael.carteirinha.feature.professor.unidadescurriculares.domain.model.UnidadeCurricularProfessor
import com.raphael.carteirinha.feature.professor.unidadescurriculares.domain.repository.UnidadeCurricularProfessorRepository
import retrofit2.HttpException
import java.io.IOException

class ApiUnidadeCurricularProfessorRepositoryImpl(
    private val api: UnidadeCurricularProfessorApi
) : UnidadeCurricularProfessorRepository {

    override suspend fun listar(): Result<List<UnidadeCurricularProfessor>> {
        return runCatching {
            api.listar().map { it.toDomain() }
        }.recoverCatching { throwable ->
            throw when (throwable) {
                is HttpException -> {
                    when (throwable.code()) {
                        401 -> IllegalStateException("Sua sessão expirou. Faça login novamente.")
                        403 -> IllegalStateException("Seu perfil não tem permissão para consultar as unidades curriculares do professor.")
                        else -> IllegalStateException("Erro ao carregar unidades curriculares (" + throwable.code() + ").")
                    }
                }
                is IOException ->
                    IllegalStateException("Não foi possível conectar à API. Verifique se ela está rodando.")
                else ->
                    IllegalStateException(throwable.message ?: "Erro ao carregar unidades curriculares.")
            }
        }
    }
}
