package com.saa.staff.interfaces

import com.saa.staff.models.Course
import com.saa.staff.models.User
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FirebaseCloudService{
    @GET("hello-world")
    suspend fun helloWorld(): String

    @POST("sign-up-staff")
    suspend fun signUp(@Body user: User): Boolean

    @POST("check-email")
    suspend fun checkEmail(@Body email: String): Boolean

    @Serializable
    data class LoginInformation(val email: String, val password: String)
    @POST("login-staff")
    suspend fun login(@Body loginInformation: LoginInformation):Boolean

    @POST("add-course")
    suspend fun addCourse(@Body course: Course): Boolean

    @GET("get-courses")
    suspend fun getCourses(): List<Course>

    @POST("delete-course")
    suspend fun deleteCourse(@Body course: Course): Boolean

    @POST("update-course")
    suspend fun updateCourse(@Body course: Course): Boolean
}