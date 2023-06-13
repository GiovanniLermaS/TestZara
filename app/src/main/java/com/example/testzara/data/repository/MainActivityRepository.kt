package com.example.testzara.data.repository

import com.example.testzara.model.Data
import com.example.testzara.retrofit.ApiInterface
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@ActivityRetainedScoped
class MainActivityRepository(private val apiInterface: ApiInterface) : IMainActivityRepository {

    override fun getData(url: String, response: (Data) -> Unit, error: (String?) -> Unit) {
        val call = apiInterface.getRegions(url)
        call.enqueue(object : Callback<Data> {
            override fun onResponse(
                call: Call<Data>,
                response: Response<Data>
            ) {
                response.body()?.let { response(it) }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                t.message?.let { error(it) }
            }

        })
    }
}