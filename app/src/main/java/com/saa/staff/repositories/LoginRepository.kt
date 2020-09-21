package com.saa.staff.repositories

import android.util.Log
import com.saa.staff.interfaces.FirebaseCloudService
import com.saa.staff.models.User
import javax.inject.Inject

class LoginRepository @Inject constructor(var client: FirebaseCloudService) {
    suspend fun signUp(user: User): Boolean {
        return try {
            client.signUp(user)
            true
        }catch (ex: Exception){
            Log.d("Err",ex.toString())
            false
        }
    }

    suspend fun checkEmail(email: String): Boolean{
        return try {
            client.checkEmail(email)
        }catch (ex: Exception){
            false
        }
    }

    suspend fun login(email: String, password: String): Boolean{
        return try {
            client.login(FirebaseCloudService.LoginInformation(email, password))
        }catch (ex: Exception){
            Log.d("Err",ex.toString())
            false
        }
    }

    suspend fun helloWorld() = client.helloWorld()


}