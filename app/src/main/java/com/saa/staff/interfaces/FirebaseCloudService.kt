package com.saa.staff.interfaces

import com.saa.staff.models.User
import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FirebaseCloudService{
    @GET("hello-world")
    suspend fun helloWorld(): String

    @POST("sign-up-staff")
    suspend fun signUp(@Body user: User): String

    @POST("check-email")
    suspend fun checkEmail(@Body email: String): String
    @Serializable
    data class LoginInformation(val email: String, val password: String)
    @POST("login-staff")
    suspend fun login(@Body loginInformation: LoginInformation):Boolean
}