package com.example.nimbus_jose_jwt_test.model.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TestRetrofit {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://karuta-api-metering.kkbox-testing.com.tw/")
        .client(createClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideRetrofit() = retrofit

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//            .connectionPool(ConnectionPool(0, 5, TimeUnit.MINUTES))
//            .protocols(listOf(Protocol.HTTP_1_1))
        return okHttpClientBuilder.build()
    }
}