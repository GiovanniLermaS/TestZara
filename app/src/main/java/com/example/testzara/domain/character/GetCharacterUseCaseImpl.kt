package com.example.testzara.domain.character

import com.example.testzara.data.model.Data
import com.example.testzara.data.model.Episode
import com.example.testzara.data.repository.main.IMainActivityRepository
import javax.inject.Inject

class GetCharacterUseCaseImpl @Inject constructor(private val iMainActivityRepository: IMainActivityRepository) :
    GetCharacterUseCase {
    override suspend fun getCharacters(
        page: Int,
        response: (Data) -> Unit,
        error: (String?) -> Unit
    ) {
        iMainActivityRepository.getCharacters(page, { response(it) }, { error(it) })
    }

    override suspend fun getEpisode(
        url: String,
        response: (Episode) -> Unit,
        error: (String?) -> Unit
    ) {
        iMainActivityRepository.getEpisode(url, { response(it) }, { error(it) })
    }
}