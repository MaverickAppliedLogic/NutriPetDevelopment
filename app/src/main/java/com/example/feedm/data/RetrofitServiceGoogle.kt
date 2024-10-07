package com.example.feedm.data

import com.example.feedm.data.model.remoteResultModel.GoogleRemoteResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitServiceGoogle{
    @GET
    suspend fun searchPhoto(@Url url: String): GoogleRemoteResult
}

object RetrofitServiceGoogleFactory{
    fun makeRetrofitServiceGoogle(): RetrofitServiceGoogle{
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/customsearch/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitServiceGoogle::class.java)
    }
}