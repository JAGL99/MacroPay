package com.example.macropay.data.networks

import com.example.macropay.data.models.ResponseModel
import com.example.macropay.utils.Constant.BASE_URL
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Url

interface MacroPayService {

    @Multipart
    @POST
    suspend fun getToken(
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Url url: String
    ): Response<ResponseModel>

    companion object {

        private var service: MacroPayService? = null

        fun createMacroPayService(): MacroPayService {

            val tempInstance = service
            if (tempInstance != null) {
                return tempInstance
            }

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MacroPayService::class.java).apply {
                    service = this
                    return this
                }

        }
    }
}