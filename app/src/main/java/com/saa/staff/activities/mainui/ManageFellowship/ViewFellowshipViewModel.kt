package com.saa.staff.activities.mainui.ManageFellowship

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.saa.staff.models.Course
import com.saa.staff.models.Fellowship

class ViewFellowshipViewModel @ViewModelInject constructor() : ViewModel() {
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

}