package com.example.projeto_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projeto_api.data.domain.Digimon
import com.example.projeto_api.ui.theme.ProjetoapiTheme
import com.example.projeto_api.views.DigimonDetailScreen
import com.example.projeto_api.views.DigimonListScreen
import com.example.projeto_api.views.DigimonVMFactory
import com.example.projeto_api.views.DigimonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<DigimonViewModel>() {
            DigimonVMFactory((this.applicationContext as DigimonsApplication).repository)
        }
        setContent {
            ProjetoapiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DigimonsCharacters(viewModel)
                }
            }
        }
    }
}

@Composable
fun DigimonsCharacters(
    viewModel: DigimonViewModel
) {

    val navController = rememberNavController()
    DigimonListScreen(navController = navController, digimonViewModel = viewModel)
    Scaffold() {
        NavHost(navController = navController, startDestination = "digimon"){
            composable("digimon"){
                DigimonListScreen(navController, viewModel)
            }
            composable(
                route = "digimon?name={name}",
                arguments = listOf(navArgument("name"){
                    defaultValue = ""
                    type = NavType.StringType
                })
            ){ navBackStackEntry ->
                val name = navBackStackEntry.arguments?.getString("name") ?: ""
                val digimons = getDigimon(name, viewModel)
                DigimonDetailScreen(digimons)
            }
        }
    }

}

fun getDigimon(name: String?, viewModel: DigimonViewModel): Digimon{
    viewModel.digimons.value?.forEach{
        if (name == it.name){
            return it
        }
    }

    return Digimon(
        "",
        "",
        ""
    )
}

/*
@Composable
fun DigimonsCharacters(
    viewModel: DigimonViewModel
) {
    DigimonListScreen(digimonViewModel = viewModel)
}
*/
