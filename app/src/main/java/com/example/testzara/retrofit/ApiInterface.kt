package com.example.testzara.retrofit

import com.example.testzara.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("{url}")
    fun getRegions(@Path("url") url: String): Call<Data>
}