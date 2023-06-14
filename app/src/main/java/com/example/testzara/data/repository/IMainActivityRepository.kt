package com.example.testzara.data.repository

import com.example.testzara.model.Data
import com.example.testzara.model.Episode

interface IMainActivityRepository {
    suspend fun getCharacters(
        page: Int,
        response: (Data) -> Unit,
        error: (String?) -> Unit
    )

    suspend fun getEpisode(
        url: String,
        response: (Episode) -> Unit,
        error: (String?) -> Unit
    )
}