package com.example.testkoombea.retrofit

import com.example.testkoombea.model.Data
import com.example.testkoombea.model.Episode
import com.example.testkoombea.util.CHARACTERS
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