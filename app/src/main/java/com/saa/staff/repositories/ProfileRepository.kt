package com.saa.staff.repositories

import android.util.Log
import com.saa.staff.interfaces.FirebaseCloudService
import com.saa.staff.models.User
import javax.inject.Inject

class ProfileRepository @Inject constructor(var client: FirebaseCloudService) {
    suspend fun signUp(user: User): Boolean {
        return try {
            client.signUp(user)
            true
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            false
        }
    }

    suspend fun checkEmail(email: String): Boolean{
        return try {
            client.checkEmail(email)
        } catch (ex: Exception) {
            false
        }
    }

    suspend fun login(email: String, password: String): String {
        return try {
            client.login(FirebaseCloudService.LoginInformation(email, password))
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            // return an empty string for the error, TODO a more descriptive error message
            ""
        }
    }

    // TODO communicate with the backend
    suspend fun updateUser(user: User): Boolean {
        return try {
            client.updateUser(user)
        } catch (ex: Exception) {
            false
        }
    }

    suspend fun getUserInfo(uuid: String): User {
        return client.getUserInfo(uuid)
    }


    suspend fun helloWorld() = client.helloWorld()


}