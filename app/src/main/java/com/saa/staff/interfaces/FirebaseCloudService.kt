package com.saa.staff.interfaces

import com.saa.staff.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FirebaseCloudService{
    @GET("helloWorld")
    suspend fun helloWorld(): String

    @POST("signUp")
    suspend fun signUp(@Body user: User): String

    @POST("checkEmail")
    suspend fun checkEmail(@Body email: String): String

    data class LoginInformation(val email: String, val password: String)
    @POST("login")
    suspend fun login(@Body loginInformation: LoginInformation):String
}