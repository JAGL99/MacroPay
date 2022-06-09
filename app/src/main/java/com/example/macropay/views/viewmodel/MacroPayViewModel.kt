package com.example.macropay.views.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.macropay.domain.GetTokenUseCase
import com.example.macropay.domain.model.User
import com.example.macropay.utils.MacroPayApplication.Companion.mSharedPreferences
import kotlinx.coroutines.launch

class MacroPayViewModel: ViewModel() {

    private val getTokenUseCase = GetTokenUseCase()

    private val userModel = MutableLiveData<User>()

    fun onCreate(email: String, password: String){
        viewModelScope.launch {
            val token = getTokenUseCase(email, password)
            if (!token.isNullOrEmpty()){
                Log.i("Token",token)
                mSharedPreferences.storeToken(token)
            }
        }
    }

}