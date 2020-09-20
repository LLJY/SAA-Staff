package com.saa.staff.activities.mainui.ManageCourses

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.saa.staff.models.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ManageCoursesViewModel @ViewModelInject constructor(private var repository: ManageCourseRepository) : ViewModel() {
    var courses: List<Course> = ArrayList()

    fun getCourses()= liveData(Dispatchers.IO) { emit(repository.getCourses()) }

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