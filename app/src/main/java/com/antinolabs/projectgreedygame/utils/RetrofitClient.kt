package com.antinolabs.projectgreedygame.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private val client = OkHttpClient().newBuilder()
        .connectTimeout(0, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(Constant.Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val retrofitApiSerwithoutInterceptor: RetrofitApiService by lazy {
        retrofit.create(RetrofitApiService::class.java)
    }
}