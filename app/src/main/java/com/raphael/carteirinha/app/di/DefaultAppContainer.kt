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

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/"
        private const val USE_FAKE_LOGIN_REPOSITORY = false
    }
}