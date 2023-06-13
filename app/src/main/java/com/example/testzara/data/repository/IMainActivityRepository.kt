package com.example.testzara.data.repository

import com.example.testzara.model.Data

interface IMainActivityRepository {
    fun getData(
        url: String,
        response: (Data) -> Unit,
        error: (String?) -> Unit
    )
}