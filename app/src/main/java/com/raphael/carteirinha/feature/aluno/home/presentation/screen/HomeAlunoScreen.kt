package com.raphael.carteirinha.feature.aluno.home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.raphael.carteirinha.feature.login.domain.model.UsuarioLogado
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.raphael.carteirinha.feature.aluno.home.presentation.component.BotaoNavegacao

@Composable
fun HomeAlunoScreen(
    usuarioLogado: UsuarioLogado,
    onCarteirinhaClick: () -> Unit,
    onUnidadesCurricularesClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
    ) {

        Text(
            text = "Portal do Aluno",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Acesse suas informações acadêmicas.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 6.dp)
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp,
            shadowElevation = 3.dp
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Text(
                    text = "Olá, ${usuarioLogado.nome}!",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "O que você deseja acessar?",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(
                        top = 4.dp,
                        bottom = 24.dp
                    )
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    BotaoNavegacao(
                        text = "Carteirinha Digital",
                        onClick = {
                            onCarteirinhaClick()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    BotaoNavegacao(
                        text = "Unidades Curriculares",
                        onClick = {
                            onUnidadesCurricularesClick()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

