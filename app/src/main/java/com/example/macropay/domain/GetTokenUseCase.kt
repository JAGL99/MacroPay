package com.example.macropay.domain

import com.example.macropay.data.MacroPayRepository

class GetTokenUseCase() {

    private val repository: MacroPayRepository = MacroPayRepository()

    suspend operator fun invoke(email: String, password: String) =
        repository.getToken(email, password)
}