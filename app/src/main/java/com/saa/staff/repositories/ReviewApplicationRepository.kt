package com.saa.staff.repositories

import com.saa.staff.interfaces.RetrofitService
import com.saa.staff.models.UserApplication
import javax.inject.Inject

class ReviewApplicationRepository @Inject constructor(private val retrofit: RetrofitService) {
    /**
     * Gets all applicants regardless
     */
    suspend fun getApplications(courseTypeIndex: Int, courseUUID: String): List<UserApplication> {
        try {
            // get the difference applicants based of the course provided
            when (courseTypeIndex) {
                0 -> return retrofit.getCourseApplications(courseUUID)
                1 -> return retrofit.getFellowshipApplications(courseUUID)
                3 -> return retrofit.getScholarshipApplications(courseUUID)
                2 -> return retrofit.getDiplomaApplications(courseUUID)
            }
        } catch (ex: Exception) {
            return listOf()
        }
        return listOf()

    }
}