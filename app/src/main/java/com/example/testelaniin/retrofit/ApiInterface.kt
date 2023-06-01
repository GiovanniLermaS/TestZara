package com.example.testelaniin.retrofit

import com.example.testelaniin.model.Region
import com.example.testelaniin.util.GET_REGIONS
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET(GET_REGIONS)
    fun getRegions(): Call<Region>
}