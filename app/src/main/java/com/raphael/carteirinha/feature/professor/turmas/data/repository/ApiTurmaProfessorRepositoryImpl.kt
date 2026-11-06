package com.raphael.carteirinha.feature.professor.turmas.data.repository

import com.raphael.carteirinha.feature.professor.turmas.data.remote.service.TurmaProfessorApi
import com.raphael.carteirinha.feature.professor.turmas.domain.model.TurmaProfessor
import com.raphael.carteirinha.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import retrofit2.HttpException
import java.io.IOException

class ApiTurmaProfessorRepositoryImpl(
    private val api: TurmaProfessorApi
) : TurmaProfessorRepository {

    override suspend fun listar(): Result<List<TurmaProfessor>> {
        return runCatching {
            api.listar().map { it.toDomain() }
        }.recoverCatching { throwable ->
            throw when (throwable) {
                is HttpException -> {
                    when (throwable.code()) {
                        401 -> IllegalStateException("Sua sessão expirou. Faça login novamente.")
                        403 -> IllegalStateException("Seu perfil não tem permissão para consultar as turmas do professor.")
                        else -> IllegalStateException(
                            "Erro ao carregar turmas (" + throwable.code() + ")."
                        )
                    }
                }
                is IOException ->
                    IllegalStateException("Não foi possível conectar à API. Verifique se ela está rodando.")
                else ->
                    IllegalStateException(throwable.message ?: "Erro ao carregar turmas.")
            }
        }
    }
}
