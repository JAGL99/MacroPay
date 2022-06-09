package com.example.macropay.utils

import android.app.Application
import android.content.SharedPreferences
import android.widget.Toast
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class MacroPayApplication : Application() {

    companion object {
        lateinit var mSharedPreferences: MSharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        mSharedPreferences = MSharedPreferences()
    }

    inner class MSharedPreferences {

        private val preferences: SharedPreferences =
            applicationContext.getSharedPreferences("login", MODE_PRIVATE)

        private var encryptedPreferences: SharedPreferences? = null


        fun theUserIsRegistered(): Boolean {
            return try {
                preferences.getBoolean("success", false)
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }

        }

        fun storeToken(token: String): Boolean {
            return try {
                setEncryptShared()
                preferences.edit().putBoolean("success", true).apply()
                encryptedPreferences?.edit()?.putString("token",token)?.apply()
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        fun getToken(): String{
            return try {
                encryptedPreferences?.getString("token","")!!
            }catch (e: java.lang.Exception){
                e.printStackTrace()
                ""
            }
        }

        private fun setEncryptShared() {

            if (encryptedPreferences != null){
                return
            }

            val encript = try {
                MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                null
            }
            if (!encript.isNullOrEmpty()) {
                try {
                    encryptedPreferences = EncryptedSharedPreferences.create(
                        "user",
                        encript,
                        applicationContext,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    )
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }

            } else {
                Toast.makeText(this@MacroPayApplication, "Something faild", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}