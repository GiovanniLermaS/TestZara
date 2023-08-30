package com.example.testzara.domain.character

import com.example.testzara.data.model.Data
import com.example.testzara.data.model.Episode

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