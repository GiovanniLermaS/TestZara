package com.example.testkoombea.view.viewmodel.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testkoombea.data.repository.main.IMainActivityRepository
import com.example.testkoombea.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.Realm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val mainActivityRepository: IMainActivityRepository,
    private val realm: Realm
) :
    ViewModel() {

    private var page = 1

    private val _data = MutableStateFlow<List<Character>>(emptyList())
    val data: StateFlow<List<Character>> = _data.asStateFlow()

    private val _error = MutableStateFlow<String?>("")
    val error: StateFlow<String?> = _error.asStateFlow()

    fun getCharacters() {
        viewModelScope.launch {
            mainActivityRepository.getCharacters(page, {
                page += 1
                it.results.withIndex().forEach { (index, character) ->
                    if (index == it.results.lastIndex) {
                        getEpisode(character, it.results, true)
                    } else getEpisode(character, it.results, false)
                }
            }, {
                _error.value = it
            })
        }
    }

    private fun getEpisode(
        character: Character,
        results: ArrayList<Character>,
        isLastItem: Boolean
    ) {
        viewModelScope.launch {
            mainActivityRepository.getEpisode(character, {
                if (isLastItem) {
                    results.sortBy { character -> character.id }
                    _data.value += results
                }
            }, {
                _error.value = it
            })
        }
    }
}