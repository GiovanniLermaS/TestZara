package com.example.testzara.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testzara.data.repository.IMainActivityRepository
import com.example.testzara.model.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val mainActivityRepository: IMainActivityRepository) :
    ViewModel() {

    private val _data = MutableLiveData<Data>()
    val data: LiveData<Data> = _data

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getCharacters() {
        mainActivityRepository.getCharacters(1, {
            _data.postValue(it)
        }, {
            _error.postValue(it)
        })
    }
}