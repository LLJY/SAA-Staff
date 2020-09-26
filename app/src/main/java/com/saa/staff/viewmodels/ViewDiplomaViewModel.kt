package com.saa.staff.viewmodels

import androidx.lifecycle.ViewModel
import com.saa.staff.models.Diploma

class ViewDiplomaViewModel : ViewModel() {
    var diploma = Diploma(
        "aaa",
        "",
        0f,
        "",
        System.currentTimeMillis(),
        System.currentTimeMillis(),
        System.currentTimeMillis()
    )
}