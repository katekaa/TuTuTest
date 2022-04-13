package com.example.tututest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tututest.model.Api
import com.example.tututest.model.Character
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class CharacterViewModel: ViewModel() {

    private val _characters = MutableLiveData<MutableList<Character>>()
    val characters = _characters
    private val _status = MutableLiveData<ApiStatus>()
    val status = _status
    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character


    init {
        getCharactersList()
    }

    fun getCharactersList() {
        var list = listOf<Character>()
        viewModelScope.launch {

//            _status.value = ApiStatus.LOADING
            try {
                _characters.value = Api.retrofitService.getCharacters().data.results.map{ it.toCharacter() }.toMutableList()
                Log.d("!!", "smt")
                _status.value = ApiStatus.DONE

            } catch (e: Exception) {
                Log.d("!", "error u nas $e")
                _status.value = ApiStatus.ERROR
                _characters.value = mutableListOf()
            }

        }
    }


    fun onCharacterClicked(character: Character) {
        _character.value = character
        Log.d("!!", "supposed character $character")
    }

}