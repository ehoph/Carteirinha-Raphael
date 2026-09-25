package com.raphael.carteirinha.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.raphael.carteirinha.app.di.AppContainer
import com.raphael.carteirinha.app.navigation.AppNavHost
import com.raphael.carteirinha.core.designsystem.theme.CarteirinhaDigital2DEVEST_BTheme

@Composable
fun App(container: AppContainer) {
    CarteirinhaDigital2DEVEST_BTheme() {
        val navController = rememberNavController()
        AppNavHost(
            navController = navController,
            container=container
        )
    }
}