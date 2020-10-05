package com.saa.staff.repositories

import android.util.Log
import com.saa.staff.interfaces.RetrofitService
import com.saa.staff.models.Course
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ManageCourseRepository @Inject constructor(var retrofit: RetrofitService) {
    suspend fun getCourses(): List<Course> {
        return try {
            retrofit.getCourses()
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
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