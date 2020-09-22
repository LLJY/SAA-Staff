package com.saa.staff.activities.mainui.ManageScholarship

import android.util.Log
import com.saa.staff.interfaces.FirebaseCloudService
import com.saa.staff.models.Scholarship
import javax.inject.Inject

class ManageScholarshipRepository @Inject constructor(retrofit: FirebaseCloudService) {
    // TODO use retrofit
    suspend fun getScholarships(): List<Scholarship> {
        return try {
            listOf(
                Scholarship(
                    "aaa",
                    "An Interesting Scholarship",
                    "GCE O Levels",
                    "Lots of benefits",
                    10,
                    "Some very long lorem ipsum outline im lazy to add because I don't currently have internet"
                )
            )
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            // return empty list if error
            listOf()
        }
    }

    suspend fun addScholarship(scholarship: Scholarship): Boolean {
        return true
    }

    suspend fun updateScholarship(scholarship: Scholarship): Boolean {
        return true
    }

    suspend fun deleteScholarship(scholarship: Scholarship): Boolean {
        return true
    }
}