package com.saa.staff.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.models.Course
import com.saa.staff.repositories.ManageCourseRepository
import kotlinx.coroutines.Dispatchers

class ManageCoursesViewModel @ViewModelInject constructor(private var repository: ManageCourseRepository) : ViewModel() {
    var courses: List<Course>? = null

    fun getCourses(refresh: Boolean = false)= liveData(Dispatchers.IO) { emit(if(courses==null||refresh)repository.getCourses()else courses) }

    /**
     * Adds course, returns boolean for successful or not
     */
    fun addCourse(course: Course): LiveData<Boolean>{
        return liveData(Dispatchers.IO) { emit(repository.addCourse(course)) }
    }
    /**
     * deletes course, returns boolean for successful or not
     */
    fun deleteCourse(course: Course): LiveData<Boolean>{
        return liveData(Dispatchers.IO) { emit(repository.deleteCourse(course) )}
    }
    /**
     * Updates course, returns boolean for successful or not
     */
    fun updateCourse(course: Course): LiveData<Boolean>{
        return liveData(Dispatchers.IO) { emit(repository.updateCourse(course)) }
    }
}