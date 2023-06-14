package com.example.testzara.view.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testzara.data.repository.IMainActivityRepository
import com.example.testzara.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val mainActivityRepository: IMainActivityRepository) :
    ViewModel() {

    private val _data = mutableStateOf<ArrayList<Character>?>(null)
    val data: State<ArrayList<Character>?> = _data

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun getCharacters() {
        viewModelScope.launch {
            mainActivityRepository.getCharacters(1, {
                val list = ArrayList<Character>()
                it.results.withIndex().forEach { (index, character) ->
                    if (index == it.results.lastIndex) {
                        getEpisode(character, list, true)
                    } else getEpisode(character, list, false)
                }
            }, {
                _error.value = it
            })
        }
    }

    private fun getEpisode(character: Character, list: ArrayList<Character>, isLastItem: Boolean) {
        viewModelScope.launch {
            mainActivityRepository.getEpisode(character.episode[0], { episode ->
                character.first_episode = episode.name
                list.add(character)
                if (isLastItem) {
                    list.sortBy { character -> character.id }
                    _data.value = list
                }
            }, {
                _error.value = it
            })
        }
    }
}