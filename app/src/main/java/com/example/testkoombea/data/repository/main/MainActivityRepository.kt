package com.example.testkoombea.data.repository.main

import com.example.testkoombea.model.Character
import com.example.testkoombea.model.Data
import com.example.testkoombea.retrofit.ApiInterface
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query

@ActivityRetainedScoped
class MainActivityRepository(
    private val apiInterface: ApiInterface,
    private val realm: Realm
) : IMainActivityRepository {

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
        character: Character,
        response: () -> Unit,
        error: (String?) -> Unit
    ) {
        val episode = apiInterface.getEpisode(character.episode[0])
        if (episode.isSuccessful) {
            val episode = episode.body() ?: throw Exception("Empty response body")
            character.firstEpisode = episode.name
            val exists = realm.query<Character>("id == $0", character.id).find()
            if (exists.size <= 0)
                realm.writeBlocking {
                    copyToRealm(character).apply {
                        name = character.name
                        status = character.status
                        species = character.species
                        image = character.image
                        firstEpisode = character.firstEpisode
                    }
                }
            return response()
        } else error(episode.message())
    }
}