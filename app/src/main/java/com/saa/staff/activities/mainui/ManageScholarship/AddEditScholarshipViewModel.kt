package com.saa.staff.activities.mainui.ManageScholarship

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.saa.staff.models.Scholarship

class AddEditScholarshipViewModel @ViewModelInject constructor() : ViewModel() {
    var isEdit = false
    var scholarship = Scholarship(
        "",
        "",
        "",
        "",
        0,
        ""
    )

    /**
     * Resets viewmodel to get rid of previous values
     */
    fun resetViewModel() {
        isEdit = false
        scholarship = Scholarship(
            "",
            "",
            "",
            "",
            0,
            ""
        )
    }
}