package com.raphael.carteirinha.feature.professor.unidadescurriculares.presentation.screen

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
import com.raphael.carteirinha.feature.professor.unidadescurriculares.presentation.UnidadeCurricularProfessorViewModel
import com.raphael.carteirinha.feature.professor.unidadescurriculares.presentation.component.UnidadeCurricularProfessorCard

@Composable
fun UnidadesCurricularesProfessorScreen(
    modifier: Modifier = Modifier,
    viewModel: UnidadeCurricularProfessorViewModel
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
            text = "Minhas Unidades Curriculares",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Acompanhe as UCs que você ministra e suas respectivas turmas.",
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
                        onClick = { viewModel.carregar() },
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
                        items = uiState.unidades,
                        key = { it.id }
                    ) { unidade ->
                        UnidadeCurricularProfessorCard(unidade = unidade)
                    }
                }
            }
        }
    }
}
