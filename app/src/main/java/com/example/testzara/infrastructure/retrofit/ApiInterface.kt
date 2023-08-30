package com.example.testzara.infrastructure.retrofit

import com.example.testzara.data.model.Data
import com.example.testzara.data.model.Episode
import com.example.testzara.util.CHARACTERS
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