package com.saa.staff.repositories

import android.util.Log
import com.saa.staff.interfaces.RetrofitService
import com.saa.staff.models.ResetPasswordModel
import com.saa.staff.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(var client: RetrofitService) {
    suspend fun signUp(user: User): Boolean {
        return try {
            client.signUp(user)
            true
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            false
        }
    }

    suspend fun resetPassword(email: String, password: String): Boolean {
        return try {
            client.resetPassword(ResetPasswordModel(email, password))
        } catch (ex: Exception) {
            false
        }
    }

    suspend fun login(email: String, password: String): String {
        return try {
            client.login(RetrofitService.LoginInformation(email, password))
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