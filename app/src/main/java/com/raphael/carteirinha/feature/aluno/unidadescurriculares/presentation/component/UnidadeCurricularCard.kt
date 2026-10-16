package com.raphael.carteirinha.feature.aluno.unidadescurriculares.presentation.component

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raphael.carteirinha.feature.aluno.unidadescurriculares.domain.model.UnidadeCurricular

@Composable
fun UnidadeCurricularCard(
    modifier: Modifier = Modifier,
    unidadeCurricular: UnidadeCurricular
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
                text = unidadeCurricular.nome,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Professor: ${unidadeCurricular.professor}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            HorizontalDivider(
                modifier = Modifier.padding(
                    vertical = 4.dp
                ),
                color = MaterialTheme.colorScheme.outlineVariant
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                NotaItem(
                    titulo = "N1",
                    valor = unidadeCurricular.nota1.toString(),
                    modifier = Modifier.weight(1f)
                )

                NotaItem(
                    titulo = "N2",
                    valor = unidadeCurricular.nota2.toString(),
                    modifier = Modifier.weight(1f)
                )

                NotaItem(
                    titulo = "Média",
                    valor = unidadeCurricular.media.toString(),
                    modifier = Modifier.weight(1f)
                )
            }

            Text(
                text = "Faltas: ${unidadeCurricular.faltas}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun NotaItem(
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
                vertical = 10.dp,
                horizontal = 8.dp
            )
        ) {

            Text(
                text = titulo,
                style = MaterialTheme.typography.labelMedium,
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

@Preview(showBackground = true)
@Composable
fun UnidadeCurricularCardPreview() {

    UnidadeCurricularCard(
        unidadeCurricular = UnidadeCurricular(
            id = "1",
            nome = "Matemática",
            professor = "Dr. Silva",
            nota1 = 8.5,
            nota2 = 7.0,
            media = 7.75,
            faltas = 2
        )
    )
}