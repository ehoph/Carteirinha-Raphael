package com.raphael.carteirinha.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.raphael.carteirinha.app.di.AppContainer
import com.raphael.carteirinha.app.session.SessionViewModel
import com.raphael.carteirinha.feature.carteirinha.presetantion.screen.CarteirinhaScreen
import com.raphael.carteirinha.feature.home_aluno.presentation.screen.HomeScreen
import com.raphael.carteirinha.feature.login.presentation.screen.LoginScreen
import com.raphael.carteirinha.feature.unidadecurriculares.presentation.UnidadeCurricularViewModel
import com.raphael.carteirinha.feature.unidadecurriculares.presentation.factory.UnidadeCurricularViewModelFactory
import com.raphael.carteirinha.feature.unidadecurriculares.presentation.screen.UnidadeCurricularScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    sessionViewModel: SessionViewModel = viewModel(),
    container: AppContainer,
) {
    val usuarioLogado by sessionViewModel.usuarioLogado.collectAsStateWithLifecycle()
    val usuario = usuarioLogado

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route) {

            LoginScreen(
                navController = navController,
                onLoginSucesso = { usuario ->
                    container.authTokenStore.setToken(usuario.token)
                    sessionViewModel.setUsuarioLogado(usuario)
                    navController.navigate(Routes.HomeAluno.route)
                }
            )
        }

        composable(Routes.Carteirinha.route) {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                CarteirinhaScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }

        composable(Routes.HomeAluno.route) {

            if (usuario == null) {
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.Login.route)
                }
            } else {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        composable(Routes.UCAluno.route) {

            if (usuario == null) {
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.Login.route)
                }

            } else {
                val unidadeCurricularFactory = remember(
                    container.unidadeCurricularRepository
                ) {
                    UnidadeCurricularViewModelFactory(
                        repository = container.unidadeCurricularRepository
                    )
                }

                val unidadeCurricularViewModel: UnidadeCurricularViewModel = viewModel(
                    factory = unidadeCurricularFactory
                )
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    UnidadeCurricularScreen(
                        modifier =Modifier.padding(innerPadding),
                        viewModel =unidadeCurricularViewModel
                    )
                }
            }
        }
    }
}