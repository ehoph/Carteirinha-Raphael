package com.raphael.carteirinha.app.di

import com.raphael.carteirinha.core.auth.SessionTokenStore
import com.raphael.carteirinha.core.network.NetworkClient
import com.raphael.carteirinha.feature.aluno.unidadescurriculares.data.remote.service.UnidadeCurricularApi
import com.raphael.carteirinha.feature.aluno.unidadescurriculares.data.repository.ApiUnidadeCurricularRepositoryImpl
import com.raphael.carteirinha.feature.aluno.unidadescurriculares.domain.repository.UnidadeCurricularRepository
import com.raphael.carteirinha.feature.login.data.remote.service.AuthApi
import com.raphael.carteirinha.feature.login.data.repository.ApiLoginRepositoryImpl
import com.raphael.carteirinha.feature.login.data.repository.FakeAuthRepository
import com.raphael.carteirinha.feature.login.domain.repository.LoginRepository
import com.raphael.carteirinha.feature.professor.turmas.data.remote.service.TurmaProfessorApi
import com.raphael.carteirinha.feature.professor.turmas.data.repository.ApiTurmaProfessorRepositoryImpl
import com.raphael.carteirinha.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import com.raphael.carteirinha.feature.professor.unidadescurriculares.data.remote.service.UnidadeCurricularProfessorApi
import com.raphael.carteirinha.feature.professor.unidadescurriculares.data.repository.ApiUnidadeCurricularProfessorRepositoryImpl
import com.raphael.carteirinha.feature.professor.unidadescurriculares.domain.repository.UnidadeCurricularProfessorRepository

class DefaultAppContainer : AppContainer {

    override val sessionTokenStore: SessionTokenStore = SessionTokenStore()

    private val networkClient = NetworkClient(
        baseUrl = BASE_URL,
        sessionTokenStore = sessionTokenStore
    )

    private val authApi: AuthApi by lazy {
        networkClient.createPublic(AuthApi::class.java)
    }

    private val unidadeCurricularApi: UnidadeCurricularApi by lazy {
        networkClient.createAuthenticated(UnidadeCurricularApi::class.java)
    }

    private val turmaProfessorApi: TurmaProfessorApi by lazy {
        networkClient.createAuthenticated(TurmaProfessorApi::class.java)
    }

    private val unidadeCurricularProfessorApi: UnidadeCurricularProfessorApi by lazy {
        networkClient.createAuthenticated(UnidadeCurricularProfessorApi::class.java)
    }

    override val loginRepository: LoginRepository by lazy {
        if (USE_FAKE_LOGIN_REPOSITORY) {
            FakeAuthRepository()
        } else {
            ApiLoginRepositoryImpl(api = authApi)
        }
    }

    override val unidadeCurricularRepository: UnidadeCurricularRepository by lazy {
        ApiUnidadeCurricularRepositoryImpl(api = unidadeCurricularApi)
    }

    override val turmaProfessorRepository: TurmaProfessorRepository by lazy {
        ApiTurmaProfessorRepositoryImpl(api = turmaProfessorApi)
    }

    override val unidadeCurricularProfessorRepository: UnidadeCurricularProfessorRepository by lazy {
        ApiUnidadeCurricularProfessorRepositoryImpl(api = unidadeCurricularProfessorApi)
    }

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/"
        private const val USE_FAKE_LOGIN_REPOSITORY = false
    }
}
