package com.example.testzara.retrofit

import com.example.testzara.model.Data
import com.example.testzara.model.Episode
import com.example.testzara.util.CHARACTERS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiInterface {

    @GET(CHARACTERS)
    fun getCharacters(@Query("page") page: Int): Call<Data>

    @GET("/{idCharacter}")
    fun getCharacter(@Path("idCharacter") idCharacter: Int): Call<Data>

    @GET
    fun getEpisode(@Url url: String): Call<Episode>
}