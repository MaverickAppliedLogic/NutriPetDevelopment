package com.example.feedm.data


import com.example.feedm.data.model.remoteResultModel.ExaRemoteResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface RetrofitServiceExa {

    @Headers("x-api-key: 615b4061-8d65-4fe9-a5e6-23e6fa36f0f7",
             "content-type: application/json")
    @POST("search")
    suspend fun listProperFoods(@Body request : SearchRequest): ExaRemoteResult
}

data class SearchRequest(
    val query: String,
    val use_autoprompt: Boolean,
    val type: String,
    val num_results: Int = 20
)

object RetrofitServiceExaFactory {
    fun makeRetrofitServiceExa(): RetrofitServiceExa{
        return Retrofit.Builder()
            .baseUrl("https://api.exa.ai/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitServiceExa::class.java)
    }
}