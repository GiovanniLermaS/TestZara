package com.example.testelaniin.data.repository

import com.example.testelaniin.model.Region
import com.example.testelaniin.retrofit.ApiInterface
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@ActivityRetainedScoped
class MainActivityRepository(private val apiInterface: ApiInterface) : IMainActivityRepository {

    override fun getRegions(response: (Region) -> Unit, error: (String?) -> Unit) {
        val call = apiInterface.getRegions()
        call.enqueue(object : Callback<Region> {
            override fun onResponse(
                call: Call<Region>,
                response: Response<Region>
            ) {
                response.body()?.let { response(it) }
            }

            override fun onFailure(call: Call<Region>, t: Throwable) {
                t.message?.let { error(it) }
            }

        })
    }
}