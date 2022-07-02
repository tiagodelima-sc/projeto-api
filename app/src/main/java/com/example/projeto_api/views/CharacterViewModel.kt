package com.example.projeto_api.views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projeto_api.data.Character
import com.example.projeto_api.network.OpenFinalSpaceApi
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private val _characterList = MutableLiveData<List<Character>>()
    val characterList: LiveData<List<Character>>
        get() = _characterList

    init {
        getCharacter()
    }


    private fun getCharacter(){
        viewModelScope.launch {
            try {
                val listResult = OpenFinalSpaceApi.retrofitService.getAuthor()
                _characterList.value = listResult
            } catch (e: Exception){
                _characterList.value = null
                Log.d("GetCharacter"," ${e.message}")
            }
        }
    }
}