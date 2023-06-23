package com.example.testkoombea.data.repository.main

import com.example.testkoombea.model.Character
import com.example.testkoombea.model.Data
import com.example.testkoombea.model.Episode

interface IMainActivityRepository {
    suspend fun getCharacters(
        page: Int,
        response: (Data) -> Unit,
        error: (String?) -> Unit
    )

    suspend fun getEpisode(
        character: Character,
        response: () -> Unit,
        error: (String?) -> Unit
    )
}