package com.example.macropay.views.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.macropay.R
import com.example.macropay.utils.MacroPayApplication.Companion.mSharedPreferences

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        when (mSharedPreferences.theUserIsRegistered()){
            true -> navController.navigate(R.id.action_loginFragment_to_mainFragment)
            false -> navController.navigate(R.id.loginFragment)
        }
    }
}