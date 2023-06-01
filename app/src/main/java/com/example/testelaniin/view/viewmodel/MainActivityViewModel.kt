package com.example.testelaniin.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testelaniin.data.repository.IMainActivityRepository
import com.example.testelaniin.model.Region
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val mainActivityRepository: IMainActivityRepository) :
    ViewModel() {

    private val _regions = MutableLiveData<Region>()
    val regions: LiveData<Region> = _regions

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getRegions() {
        mainActivityRepository.getRegions({
            _regions.postValue(it)
        }, {
            _error.postValue(it)
        })
    }
}