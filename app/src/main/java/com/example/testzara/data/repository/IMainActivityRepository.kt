package com.example.testzara.data.repository

import com.example.testzara.model.Data

interface IMainActivityRepository {
    fun getCharacters(
        page: Int,
        response: (Data) -> Unit,
        error: (String?) -> Unit
    )
}