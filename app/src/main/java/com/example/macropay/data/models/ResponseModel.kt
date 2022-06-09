package com.example.macropay.data.models

data class ResponseModel(
    val success: Boolean,
    val token: String?,
    val msg: String?
)
