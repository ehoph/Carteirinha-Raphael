package com.raphael.carteirinha.app.navigation.professor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.raphael.carteirinha.app.navigation.GraphRoutes
import com.raphael.carteirinha.app.navigation.ProfileGuard
import com.raphael.carteirinha.app.ui.AppShell
import com.raphael.carteirinha.feature.login.domain.model.PerfilAcesso
import com.raphael.carteirinha.feature.login.domain.model.UsuarioLogado
import com.raphael.carteirinha.feature.professor.home.presentation.screen.HomeProfessorScreen
import com.raphael.carteirinha.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import com.raphael.carteirinha.feature.professor.turmas.presentation.TurmaProfessorViewModel
import com.raphael.carteirinha.feature.professor.turmas.presentation.factory.TurmaProfessorViewModelFactory
import com.raphael.carteirinha.feature.professor.turmas.presentation.screen.TurmasProfessorScreen
import com.raphael.carteirinha.feature.professor.unidadescurriculares.domain.repository.UnidadeCurricularProfessorRepository
import com.raphael.carteirinha.feature.professor.unidadescurriculares.presentation.UnidadeCurricularProfessorViewModel
import com.raphael.carteirinha.feature.professor.unidadescurriculares.presentation.factory.UnidadeCurricularProfessorViewModelFactory
import com.raphael.carteirinha.feature.professor.unidadescurriculares.presentation.screen.UnidadesCurricularesProfessorScreen
import kotlinx.coroutines.flow.StateFlow

fun NavGraphBuilder.professorNavGraph(
    navController: NavHostController,
    usuarioLogadoFlow: StateFlow<UsuarioLogado?>,
    turmaProfessorRepository: TurmaProfessorRepository,
    unidadeCurricularProfessorRepository: UnidadeCurricularProfessorRepository,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    onLogout: () -> Unit,
    onSessaoAusente: () -> Unit,
    onPerfilInvalido: (PerfilAcesso) -> Unit
) {
    navigation(
        startDestination = ProfessorRoutes.HOME,
        route = GraphRoutes.PROFESSOR
    ) {
        composable(route = ProfessorRoutes.HOME) {
            ProfileGuard(
                usuarioFlow = usuarioLogadoFlow,
                perfilEsperado = PerfilAcesso.PROFESSOR,
                onSessaoAusente = onSessaoAusente,
                onPerfilInvalido = onPerfilInvalido
            ) { usuario ->
                AppShell(
                    title = "Início",
                    usuarioLogado = usuario,
                    canNavigateBack = false,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = {},
                    onLogout = onLogout
                ) { innerPadding ->
                    HomeProfessorScreen(
                        usuarioLogado = usuario,
                        onTurmasClick = {
                            navController.navigate(ProfessorRoutes.TURMAS)
                        },
                        onUnidadesCurricularesClick = {
                            navController.navigate(ProfessorRoutes.UNIDADES_CURRICULARES)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }

        composable(route = ProfessorRoutes.TURMAS) {
            ProfileGuard(
                usuarioFlow = usuarioLogadoFlow,
                perfilEsperado = PerfilAcesso.PROFESSOR,
                onSessaoAusente = onSessaoAusente,
                onPerfilInvalido = onPerfilInvalido
            ) { usuario ->
                val factory = remember(turmaProfessorRepository) {
                    TurmaProfessorViewModelFactory(
                        repository = turmaProfessorRepository
                    )
                }
                val turmaViewModel: TurmaProfessorViewModel = viewModel(
                    factory = factory
                )

                AppShell(
                    title = "Turmas",
                    usuarioLogado = usuario,
                    canNavigateBack = true,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onLogout = onLogout
                ) { innerPadding ->
                    TurmasProfessorScreen(
                        viewModel = turmaViewModel,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }

        composable(route = ProfessorRoutes.UNIDADES_CURRICULARES) {
            ProfileGuard(
                usuarioFlow = usuarioLogadoFlow,
                perfilEsperado = PerfilAcesso.PROFESSOR,
                onSessaoAusente = onSessaoAusente,
                onPerfilInvalido = onPerfilInvalido
            ) { usuario ->
                val factory = remember(unidadeCurricularProfessorRepository) {
                    UnidadeCurricularProfessorViewModelFactory(
                        repository = unidadeCurricularProfessorRepository
                    )
                }
                val unidadeViewModel: UnidadeCurricularProfessorViewModel = viewModel(
                    factory = factory
                )

                AppShell(
                    title = "Unidades Curriculares",
                    usuarioLogado = usuario,
                    canNavigateBack = true,
                    darkTheme = darkTheme,
                    onDarkThemeChange = onDarkThemeChange,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onLogout = onLogout
                ) { innerPadding ->
                    UnidadesCurricularesProfessorScreen(
                        viewModel = unidadeViewModel,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}
