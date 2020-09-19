package com.saa.staff.activities.mainui.ManageCourses

import android.util.Log
import com.saa.staff.interfaces.FirebaseCloudService
import com.saa.staff.models.Course
import com.saa.staff.models.User
import java.lang.Exception
import javax.inject.Inject

class ManageCourseRepository @Inject constructor(var retrofit: FirebaseCloudService) {
    suspend fun getCourses(): List<Course> {
        return try {
            retrofit.getCourses()
        }catch (ex: Exception){
            Log.d("Err",ex.toString())
            // return empty list if error
            listOf()
        }
    }
    suspend fun addCourse(course: Course): Boolean {
        return try {
            retrofit.addCourse(course)
        }catch (ex: Exception){
            Log.d("Err",ex.toString())
            // return empty list if error
            false
        }
    }
    suspend fun deleteCourse(course: Course): Boolean {
        return try {
            retrofit.deleteCourse(course)
        }catch (ex: Exception){
            Log.d("Err",ex.toString())
            // return empty list if error
            false
        }
    }
    suspend fun updateCourse(course: Course): Boolean {
        return try {
            retrofit.updateCourse(course)
        }catch (ex: Exception){
            Log.d("Err",ex.toString())
            // return empty list if error
            false
        }
    }
}