package com.example.projeto_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projeto_api.ui.theme.ProjetoapiTheme
import com.example.projeto_api.views.CharacterListScreen
import com.example.projeto_api.views.CharacterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<CharacterViewModel>()
        setContent {
            ProjetoapiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FinalSpaceCharacter(viewModel)
                }
            }
        }
    }
}

@Composable
fun FinalSpaceCharacter(
    viewModel: CharacterViewModel
) {
    CharacterListScreen(characterViewModel = viewModel)
}

