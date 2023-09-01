package com.example.testzara.framework.data.datasources

import com.example.testzara.domain.Data
import com.example.testzara.domain.Episode
import com.example.testzara.framework.util.CHARACTERS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {

    @GET(CHARACTERS)
    suspend fun getCharacters(@Query("page") page: Int): Response<Data>

    @GET
    suspend fun getEpisode(@Url url: String): Response<Episode>
}