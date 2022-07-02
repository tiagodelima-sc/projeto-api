package com.example.projeto_api

import android.app.Application
import com.example.projeto_api.data.local.DigimonsDatabase
import com.example.projeto_api.data.repository.DigimonRepository

class DigimonsApplication: Application() {

    private val database: DigimonsDatabase by lazy {
        DigimonsDatabase.getInstance(this)
    }

    val repository: DigimonRepository by lazy {
        DigimonRepository(database.digimonDao())
    }

}