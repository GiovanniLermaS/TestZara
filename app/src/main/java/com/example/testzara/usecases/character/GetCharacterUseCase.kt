package com.example.testzara.usecases.character

import com.example.testzara.domain.Data
import com.example.testzara.domain.Episode

interface GetCharacterUseCase {
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