package com.raphael.carteirinha.feature.professor.unidadescurriculares.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.raphael.carteirinha.feature.professor.unidadescurriculares.domain.model.UnidadeCurricularProfessor

@Composable
fun UnidadeCurricularProfessorCard(
    unidade: UnidadeCurricularProfessor,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = unidade.nome,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Text(
                    text = "Turma " + unidade.turma,
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 7.dp
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 2.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IndicadorAula(
                    titulo = "Carga horária",
                    valor = unidade.cargaHoraria.toString() + "h",
                    modifier = Modifier.weight(1f)
                )
                IndicadorAula(
                    titulo = "Aulas",
                    valor = unidade.quantidadeAulas.toString(),
                    modifier = Modifier.weight(1f)
                )
                IndicadorAula(
                    titulo = "Realizadas",
                    valor = unidade.aulasRealizadas.toString(),
                    modifier = Modifier.weight(1f)
                )
            }

            Text(
                text = "Dias: " + unidade.diasSemana.joinToString(" • "),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Horário: " + unidade.horarioInicio + " – " + unidade.horarioFim,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun IndicadorAula(
    titulo: String,
    valor: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 10.dp
            )
        ) {
            Text(
                text = titulo,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = valor,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}
