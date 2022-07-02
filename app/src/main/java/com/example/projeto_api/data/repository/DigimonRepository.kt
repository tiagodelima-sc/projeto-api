package com.example.projeto_api.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.projeto_api.data.domain.Digimon
import com.example.projeto_api.data.local.DigimonDao
import com.example.projeto_api.data.local.asDomainModel
import com.example.projeto_api.data.source.SourceDigimonContainer
import com.example.projeto_api.data.source.asLocalModel
import com.example.projeto_api.network.OpenDigimonApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DigimonRepository(private val digimonDao: DigimonDao) {

    val digimons: LiveData<List<Digimon>> = Transformations.map(
        digimonDao.getAllDigimons()
    ){
        it.asDomainModel()
    }

    suspend fun refreshDigimons(){
        withContext(Dispatchers.IO){
            val digimons = OpenDigimonApi.retrofitService.getDigimons()
            val digimonsContainer = SourceDigimonContainer(digimons)
            digimonDao.insertAllDigimons(digimonsContainer.asLocalModel())
        }
    }

}