package com.saa.staff.interfaces

import com.saa.staff.models.*
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

    @POST("add-fellowship")
    suspend fun addFellowship(@Body fellowship: Fellowship): Boolean

    @GET("get-fellowships")
    suspend fun getFellowships(): List<Fellowship>

    @POST("delete-fellowship")
    suspend fun deleteFellowship(@Body fellowship: Fellowship): Boolean

    @POST("update-fellowship")
    suspend fun updateFellowship(@Body fellowship: Fellowship): Boolean

    @POST("add-diploma")
    suspend fun addDiploma(@Body diploma: Diploma): Boolean

    @GET("get-diplomas")
    suspend fun getDiplomas(): List<Diploma>

    @POST("delete-diploma")
    suspend fun deleteDiploma(@Body diploma: Diploma): Boolean

    @POST("update-diploma")
    suspend fun updateDiploma(@Body diploma: Diploma): Boolean

    @POST("add-scholarship")
    suspend fun addScholarship(@Body scholarship: Scholarship): Boolean

    @GET("get-scholarships")
    suspend fun getScholarships(): List<Scholarship>

    @POST("delete-scholarship")
    suspend fun deleteScholarship(@Body scholarship: Scholarship): Boolean

    @POST("update-scholarship")
    suspend fun updateScholarship(@Body scholarship: Scholarship): Boolean
}