package com.saa.staff.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.saa.staff.models.Course

class ViewCourseInfoViewModel @ViewModelInject constructor(): ViewModel() {
    var course = Course(
        "",
        "",
        0,
        0,
        0f,
        "",
        "",
        "",
        "",
        "",
        "",
        0
    )
    fun clearViewModel(){
        course = Course(
            "",
            "",
            0,
            0,
            0f,
            "",
            "",
            "",
            "",
            "",
            "",
            0
        )
    }
}