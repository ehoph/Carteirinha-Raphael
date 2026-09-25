package com.raphael.carteirinha.feature.login.data.repository

import com.raphael.carteirinha.feature.login.data.remote.network.NetworkFactory


object LoginRepositoryProvider {
    private const val USE_FAKE_REPOSITORY = false

    fun provide(): LoginRepository {
        return if (USE_FAKE_REPOSITORY) {
            FakeLoginRepositoryImpl()
        } else {
            ApiLoginRepositoryImpl(NetworkFactory.createAuthApi())
        }
    }
}