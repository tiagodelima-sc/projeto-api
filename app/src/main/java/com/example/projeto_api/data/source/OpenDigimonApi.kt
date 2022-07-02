package com.example.projeto_api.network

import com.example.projeto_api.data.domain.Digimon
import com.example.projeto_api.data.source.SourceDigimon
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://digimon-api.vercel.app"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface OpenDigimonApiService{
    @GET("api/digimon")
    suspend fun getDigimons(): List<SourceDigimon>
}

object OpenDigimonApi{
    val retrofitService: OpenDigimonApiService by lazy {
        retrofit.create(OpenDigimonApiService::class.java)
    }
}