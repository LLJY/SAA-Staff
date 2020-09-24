package com.saa.staff.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.saa.staff.models.Course
import com.saa.staff.models.Fellowship

class AddEditFellowshipViewModel @ViewModelInject constructor() : ViewModel() {
    var isEdit = false
    var courseList = ArrayList<Course>()
    // dummy data to satisfy kotlin's null safety
    var fellowShip: Fellowship = Fellowship(
        "",
        "",
        "",
        Course(
            "",
            "",
            System.currentTimeMillis() - 10000000,
            System.currentTimeMillis(),
            1000f,
            "",
            "",
            "",
            "",
            "",
            "",
            System.currentTimeMillis()
        ),
        System.currentTimeMillis()
    )

    /**
     * Sets the viewmodel to default values
     */
    fun clearViewModel(){
        fellowShip = Fellowship(
            "",
            "",
            "",
            Course(
                "",
                "",
                System.currentTimeMillis() - 10000000,
                System.currentTimeMillis(),
                1000f,
                "",
                "",
                "",
                "",
                "",
                "",
                System.currentTimeMillis()
            ),
            System.currentTimeMillis()
        )
        isEdit = false
    }
}