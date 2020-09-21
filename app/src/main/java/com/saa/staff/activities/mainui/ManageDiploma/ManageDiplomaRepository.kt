package com.saa.staff.activities.mainui.ManageDiploma

import android.util.Log
import com.saa.staff.interfaces.FirebaseCloudService
import com.saa.staff.models.Diploma
import javax.inject.Inject

class ManageDiplomaRepository @Inject constructor(var retrofit: FirebaseCloudService) {
    suspend fun getDiplomas(): List<Diploma> {
        return try {
            listOf(
                Diploma(
                    "aaa",
                    "course title",
                    0f,
                    "Some outline",
                    System.currentTimeMillis(),
                    System.currentTimeMillis(),
                    System.currentTimeMillis()
                )
            )
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            // return empty list if error
            listOf()
        }
    }

    suspend fun addDiploma(diploma: Diploma): Boolean {
        return true
    }

    suspend fun updateDiploma(diploma: Diploma): Boolean {
        return true
    }

    suspend fun deleteDiploma(diploma: Diploma): Boolean {
        return true
    }
}