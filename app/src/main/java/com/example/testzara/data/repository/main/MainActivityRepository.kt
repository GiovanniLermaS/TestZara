package com.example.testzara.data.repository.main

import com.example.testzara.data.model.Data
import com.example.testzara.data.model.Episode
import com.example.testzara.infrastructure.retrofit.ApiInterface
import dagger.hilt.android.scopes.ActivityRetainedScoped

@ActivityRetainedScoped
class MainActivityRepository(private val apiInterface: ApiInterface) : IMainActivityRepository {

    override suspend fun getCharacters(
        page: Int,
        response: (Data) -> Unit,
        error: (String?) -> Unit
    ) {
        val characters = apiInterface.getCharacters(page)
        if (characters.isSuccessful)
            return response(characters.body() ?: throw Exception("Empty response body"))
        else error(characters.message())
    }

    override suspend fun getEpisode(
        url: String,
        response: (Episode) -> Unit,
        error: (String?) -> Unit
    ) {
        val episode = apiInterface.getEpisode(url)
        if (episode.isSuccessful)
            return response(episode.body() ?: throw Exception("Empty response body"))
        else error(episode.message())
    }
}