package com.example.macropay.data

import com.example.macropay.data.networks.MacroPayService
import com.example.macropay.utils.Constant.BASE_URL
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class MacroPayRepository {

    private val service: MacroPayService = MacroPayService.createMacroPayService()

    suspend fun getToken(email: String, password: String): String {
        try {
            val email = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), email)
            val password = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), password)
            service.getToken(email, password,BASE_URL).let {
                if (it.isSuccessful && it.body()?.success == true) {
                    return it.body()?.token!!
                } else {
                    return ""
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }
}