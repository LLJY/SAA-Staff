package com.saa.staff.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.saa.staff.models.Course

class AddEditCourseViewModel @ViewModelInject constructor() : ViewModel() {
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
    var isEdit = false
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