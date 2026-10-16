package com.raphael.carteirinha.feature.aluno.unidadescurriculares.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raphael.carteirinha.feature.aluno.unidadescurriculares.presentation.UnidadeCurricularViewModel
import com.raphael.carteirinha.feature.aluno.unidadescurriculares.presentation.component.UnidadeCurricularCard

@Composable
fun UnidadeCurricularScreen(
    modifier: Modifier = Modifier,
    viewModel: UnidadeCurricularViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.carregar()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                horizontal = 20.dp,
                vertical = 16.dp
            )
    ) {

        Text(
            text = "Unidades Curriculares",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Acompanhe suas matérias, notas e faltas.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(
                top = 4.dp,
                bottom = 20.dp
            )
        )

        when {

            uiState.isLoading -> {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            uiState.errorMessage != null -> {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = uiState.errorMessage ?: "",
                        color = MaterialTheme.colorScheme.error
                    )

                    Button(
                        onClick = {
                            viewModel.carregar()
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {

                        Text("Tentar novamente")
                    }
                }
            }

            uiState.unidades.isEmpty() -> {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Nenhuma unidade curricular encontrada.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            else -> {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    items(
                        uiState.unidades
                    ) { unidadeCurricular ->

                        UnidadeCurricularCard(
                            unidadeCurricular = unidadeCurricular
                        )
                    }
                }
            }
        }
    }
}

