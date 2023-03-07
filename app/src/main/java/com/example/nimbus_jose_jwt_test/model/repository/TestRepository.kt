package com.example.nimbus_jose_jwt_test.model.repository

import com.example.nimbus_jose_jwt_test.model.network.TestRetrofit
import com.example.nimbus_jose_jwt_test.model.network.TestApi
import com.google.gson.JsonObject
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response

class TestRepository {
    private val service : TestApi by lazy { TestRetrofit.provideRetrofit().create(TestApi::class.java) }

    suspend fun testRsa(encryptedText: String): Response<JsonObject> {
        return service.testRsa(encryptedText.toRequestBody())
    }

    suspend fun testEcdh(encryptedText: String): Response<JsonObject> {
        return service.testEcdh(encryptedText.toRequestBody())
    }
}