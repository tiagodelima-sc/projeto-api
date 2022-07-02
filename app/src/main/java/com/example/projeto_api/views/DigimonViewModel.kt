package com.example.projeto_api.views

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projeto_api.network.OpenDigimonApi
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private val _characterList = MutableLiveData<List<Character>?>()
    val characterList: MutableLiveData<List<Character>?>
        get() = _characterList

    init {
        getCharacter()
    }


    private fun getCharacter(){
        viewModelScope.launch {
            try {
                val listResult = OpenDigimonApi.retrofitService.getAuthor()
                _characterList.value = listResult
            } catch (e: Exception){
                _characterList.value = null
                Log.d("GetCharacter"," ${e.message}")
            }
        }
    }
}