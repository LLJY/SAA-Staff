package com.saa.staff.interfaces

import com.saa.staff.models.*
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @GET("hello-world")
    suspend fun helloWorld(): String

    @POST("sign-up-staff")
    suspend fun signUp(@Body user: User): Boolean

    @POST("update-employee-info")
    suspend fun updateUser(@Body user: User): Boolean

    @POST("check-email")
    suspend fun checkEmail(@Body email: String): Boolean

    @Serializable
    data class LoginInformation(val email: String, val password: String)

    @POST("login-staff")
    suspend fun login(@Body loginInformation: LoginInformation): UserInfoModel

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

    @POST("get-employee-info")
    suspend fun getUserInfo(@Body uuid: String): User

    @GET("get-unapproved-staff")
    suspend fun getUnapprovedStaff(): List<Employee>

    @POST("update-staff-approval")
    suspend fun updateEmployeeApproval(@Body employee: Employee): Boolean

    // for training progress
    @POST("course-applications-approved")
    suspend fun getApprovedCourseApplications(@Body uuid: String): List<UserApplication>

    @POST("fellowship-applications-approved")
    suspend fun getApprovedFellowshipApplications(@Body uuid: String): List<UserApplication>

    @POST("diploma-applications-approved")
    suspend fun getApprovedDiplomaApplications(@Body uuid: String): List<UserApplication>

    @POST("scholarship-applications-approved")
    suspend fun getApprovedScholarshipApplications(@Body uuid: String): List<UserApplication>

    // for approval
    @POST("course-applications")
    suspend fun getCourseApplications(@Body uuid: String): List<UserApplication>

    @POST("fellowship-applications")
    suspend fun getFellowshipApplications(@Body uuid: String): List<UserApplication>

    @POST("diploma-applications")
    suspend fun getDiplomaApplications(@Body uuid: String): List<UserApplication>

    @POST("scholarship-applications")
    suspend fun getScholarshipApplications(@Body uuid: String): List<UserApplication>

    @POST("update-application-progress")
    suspend fun updateApplicationProgress(@Body userApplication: UserApplication): Boolean

    @POST("reset-password")
    suspend fun resetPassword(@Body resetPasswordModel: ResetPasswordModel): Boolean

}