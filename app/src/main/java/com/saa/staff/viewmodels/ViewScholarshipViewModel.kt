package com.saa.staff.viewmodels

import androidx.lifecycle.ViewModel
import com.saa.staff.models.Scholarship

class ViewScholarshipViewModel : ViewModel() {
    var scholarship = Scholarship(
        "aaa",
        "",
        "",
        "",
        100,
        ""
    )
}