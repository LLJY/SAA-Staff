package com.saa.staff.repositories

import com.saa.staff.interfaces.RetrofitService
import com.saa.staff.models.UserApplication
import javax.inject.Inject

class TrainingProgressRepository @Inject constructor(private val retrofit: RetrofitService) {
    suspend fun getApplications(courseTypeIndex: Int, courseUUID: String): List<UserApplication> {
        try {
            // get the difference applicants based of the course provided
            when (courseTypeIndex) {
                0 -> return retrofit.getApprovedCourseApplications(courseUUID)
                1 -> return retrofit.getApprovedFellowshipApplications(courseUUID)
                2 -> return retrofit.getApprovedScholarshipApplications(courseUUID)
                3 -> return retrofit.getApprovedDiplomaApplications(courseUUID)
            }
        } catch (ex: Exception) {
            return listOf()
        }
        return listOf()

    }

    /**
     *  Update user application by calling the method from the backend
     *  user applications can be updated for training progress and approval/rejection of applications
     *  @param userApplication the updated user application, will be serialized as json by retrofit
     */
    suspend fun updateUserApplication(userApplication: UserApplication): Boolean {
        return try {
            retrofit.updateApplicationProgress(userApplication)
        } catch (ex: Exception) {
            false
        }
    }
}