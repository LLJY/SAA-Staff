package com.saa.staff.activities.mainui.ManageDiploma

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.saa.staff.models.Diploma

class AddEditDiplomaViewModel @ViewModelInject constructor() : ViewModel() {
    var isEdit = false
    var diploma = Diploma(
        "",
        "",
        0f,
        "",
        System.currentTimeMillis(),
        System.currentTimeMillis(),
        System.currentTimeMillis()
    )

    /**
     * Clear all viewModel data
     */
    fun clearViewModel() {
        isEdit = false
        diploma = Diploma(
            "",
            "",
            0f,
            "",
            System.currentTimeMillis(),
            System.currentTimeMillis(),
            System.currentTimeMillis()
        )
    }
}