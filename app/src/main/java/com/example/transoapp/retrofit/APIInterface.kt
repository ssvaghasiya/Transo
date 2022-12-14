package com.example.transoapp.retrofit

import com.example.transoapp.pojo.ExampleData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("nasa-pictures.json")
    suspend fun getExamples(): Response<List<ExampleData>?>


}