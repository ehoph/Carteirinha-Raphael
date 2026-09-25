package com.raphael.carteirinha.core.auth

interface AuthTokenStore {

    fun getToken(): String?

    fun setToken(token: String)

    fun clearToken()
}

class InMemoryAuthTokenStore : AuthTokenStore {

    @Volatile
    private var token: String? = null

    override fun getToken(): String? {
        return token
    }

    override fun setToken(token: String) {
        this.token = token
    }

    override fun clearToken() {
        token = null
    }
}