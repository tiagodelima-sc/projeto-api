package com.example.projeto_api.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.projeto_api.R
import com.example.projeto_api.data.domain.Digimon

@Composable
fun DigimonDetailScreen(
    digimon: Digimon
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Digimon Information", textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4.copy(
                color = Color.DarkGray, fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(bottom = 25.dp)
                .width(250.dp)
        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(digimon.img)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = digimon.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(300.dp)
                .height(300.dp)
        )
        Text(
            text = "Digimon:   " + digimon.name,
            style = MaterialTheme.typography.h5.copy(
                color = Color.DarkGray, fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(top = 25.dp)
        )
        Text(
            text = "Level:   " + digimon.level,
            style = MaterialTheme.typography.h5.copy(
                color = Color.DarkGray, fontWeight = FontWeight.Bold
            )
        )
    }

}

