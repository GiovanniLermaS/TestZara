package com.example.testelaniin.retrofit

import com.example.testelaniin.model.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("{url}")
    fun getRegions(@Path("url") url: String): Call<Data>
}