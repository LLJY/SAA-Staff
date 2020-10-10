package com.saa.staff.activities.mainui.TrainingProgress

import com.saa.staff.interfaces.RetrofitService
import com.saa.staff.models.UserApplication
import javax.inject.Inject

class TrainingProgressRepository @Inject constructor(private val retrofit: RetrofitService) {
    suspend fun getApplications(courseTypeIndex: Int): List<UserApplication> {
        try {
            // get the difference applicants based of the course provided
            when (courseTypeIndex) {
                0 -> return retrofit.getApprovedCourseApplications()
                1 -> return retrofit.getApprovedFellowshipApplications()
                2 -> return retrofit.getApprovedScholarshipApplications()
                3 -> return retrofit.getApprovedDiplomaApplications()
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