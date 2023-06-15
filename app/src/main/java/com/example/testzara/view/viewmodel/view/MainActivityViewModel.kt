package com.example.testzara.view.viewmodel.view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testzara.data.repository.main.IMainActivityRepository
import com.example.testzara.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val mainActivityRepository: IMainActivityRepository) :
    ViewModel() {

    private val list = ArrayList<Character>()

    private var page = 1

    private val _showProgress = mutableStateOf(true)
    val showProgress: State<Boolean> = _showProgress

    private val _data = mutableStateOf<ArrayList<Character>?>(null)
    val data: State<ArrayList<Character>?> = _data

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun getCharacters() {
        _showProgress.value = true
        viewModelScope.launch {
            mainActivityRepository.getCharacters(page, {
                page += 1
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
                character.firstEpisode = episode.name
                list.add(character)
                if (isLastItem) {
                    list.sortBy { character -> character.id }
                    _data.value = list
                    _showProgress.value = false
                }
            }, {
                _error.value = it
            })
        }
    }
}