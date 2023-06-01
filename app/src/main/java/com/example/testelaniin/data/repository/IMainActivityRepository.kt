package com.example.testelaniin.data.repository

import com.example.testelaniin.model.Region

interface IMainActivityRepository {
    fun getRegions(
        response: (Region) -> Unit,
        error: (String?) -> Unit
    )
}