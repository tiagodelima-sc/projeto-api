package com.example.projeto_api.views

import android.util.Log
import androidx.lifecycle.*
import com.example.projeto_api.data.domain.Digimon
import com.example.projeto_api.data.repository.DigimonRepository
import com.example.projeto_api.network.OpenDigimonApi
import kotlinx.coroutines.launch
import java.io.IOException

class DigimonViewModel(private val repository: DigimonRepository) : ViewModel() {

    init {
        if(repository.digimons.value.isNullOrEmpty()){
            refreshDataFromRepository()
        }
    }

    val digimons = repository.digimons

    private val _eventNetworkError = MutableLiveData<String>("")

    private fun refreshDataFromRepository(){
        viewModelScope.launch {
            try {
                repository.refreshDigimons()
                _eventNetworkError.value = ""
            } catch (networkError: IOException){
                Log.d("Error", "networkError.message")
                _eventNetworkError.value = networkError.message
            }
        }
    }

}

class DigimonVMFactory(private val repository: DigimonRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DigimonViewModel::class.java))
            return DigimonViewModel(repository) as T
        throw IllegalAccessException("Unknown ViewModel Class")
    }
}