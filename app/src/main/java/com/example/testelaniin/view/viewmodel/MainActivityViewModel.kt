package com.example.testelaniin.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testelaniin.data.repository.IMainActivityRepository
import com.example.testelaniin.model.Data
import com.example.testelaniin.util.GET_GROUPS
import com.example.testelaniin.util.GET_REGIONS
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val mainActivityRepository: IMainActivityRepository) :
    ViewModel() {

    private val _data = MutableLiveData<Data>()
    val data: LiveData<Data> = _data

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getRegions() {
        mainActivityRepository.getData(GET_REGIONS, {
            _data.postValue(it)
        }, {
            _error.postValue(it)
        })
    }

    fun getGroups() {
        mainActivityRepository.getData(GET_GROUPS, {
            _data.postValue(it)
        }, {
            _error.postValue(it)
        })
    }

    fun getGroupInfo() {
        mainActivityRepository.getData(GET_GROUPS, {
            _data.postValue(it)
        }, {
            _error.postValue(it)
        })
    }
}