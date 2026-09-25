package com.raphael.carteirinha.app

import android.app.Application
import com.raphael.carteirinha.app.di.AppContainer
import com.raphael.carteirinha.app.di.DefaultAppContainer

class CarteirinhaApplication : Application() {
    val container: AppContainer by lazy {
        DefaultAppContainer()
    }
}