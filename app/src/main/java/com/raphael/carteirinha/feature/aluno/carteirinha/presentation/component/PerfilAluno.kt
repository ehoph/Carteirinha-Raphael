package com.raphael.carteirinha.feature.aluno.carteirinha.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raphael.carteirinha.R

@Composable
fun PerfilAluno(
    nome: String,
    curso: String,
    idFoto: Int = R.drawable.login
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(
                id = idFoto
            ),
            contentDescription = "Foto do aluno",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(135.dp)
                .clip(CircleShape)
                .border(
                    width = 4.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )

        InfoAluno(
            label = "Nome",
            value = nome
        )

        InfoAluno(
            label = "Curso",
            value = curso,
            fontSizeValue = 16.sp,
            fontWeightValue = FontWeight.Medium
        )
    }
}