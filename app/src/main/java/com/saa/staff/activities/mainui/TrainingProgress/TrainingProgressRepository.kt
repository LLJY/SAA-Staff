package com.saa.staff.activities.mainui.TrainingProgress

import com.saa.staff.interfaces.RetrofitService
import com.saa.staff.models.UserApplication
import javax.inject.Inject

class TrainingProgressRepository @Inject constructor(retrofit: RetrofitService) {
    suspend fun getApplications(courseTypeIndex: Int): List<UserApplication> {
        try {
            // get the difference applicants based of the course provided
            when (courseTypeIndex) {
                0 -> return listOf(
                    UserApplication(
                        "Lucas Lee",
                        2,
                        "aaa",
                        "aaa",
                        0
                    )
                )
                1 -> return listOf(
                    UserApplication(
                        "Lucas Lee",
                        2,
                        "aaa",
                        "aaa",
                        1
                    )
                )
                2 -> return listOf(
                    UserApplication(
                        "Lucas Lee",
                        2,
                        "aaa",
                        "aaa",
                        2
                    )
                )
                3 -> return listOf(
                    UserApplication(
                        "Lucas Lee",
                        2,
                        "aaa",
                        "aaa",
                        3
                    )
                )
            }
        } catch (ex: Exception) {
            return listOf()
        }
        return listOf()

    }

    suspend fun updateUserApplication(userApplication: UserApplication): Boolean {
        return true
    }
}