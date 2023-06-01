package com.example.testelaniin.data.repository

import com.example.testelaniin.model.Data

interface IMainActivityRepository {
    fun getData(
        url: String,
        response: (Data) -> Unit,
        error: (String?) -> Unit
    )
}