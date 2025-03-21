package com.example.feedm.core.data.services


import com.example.feedm.core.domain.remoteResultModel.ExaRemoteResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface RetrofitServiceExa {
/*Currently not working because of redesign*/
    @Headers("")
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
    fun makeRetrofitServiceExa(): RetrofitServiceExa {
        return Retrofit.Builder()
            .baseUrl("https://api.exa.ai/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitServiceExa::class.java)
    }
}