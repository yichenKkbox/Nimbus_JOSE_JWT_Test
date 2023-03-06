package com.example.nimbus_jose_jwt_test.model.network

import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TestApi {

    //https://karuta.kkbox.io/schemas/api-metering/operations/DebugEchoRsa
    @POST("v1/debug_echo/rsa")
    suspend fun testRsa(@Body data: RequestBody) : Response<JsonObject>
}