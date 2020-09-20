package com.saa.staff.activities.mainui.ManageFellowship

import android.util.Log
import com.saa.staff.interfaces.FirebaseCloudService
import com.saa.staff.models.Course
import com.saa.staff.models.Fellowship
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject

class ManageFellowshipRepository @Inject constructor(retrofit: FirebaseCloudService) {
    suspend fun getFellowships(): List<Fellowship> {
        return try {
            listOf(
                Fellowship(
                    "",
                    "An interesting fellowship",
                    "some random outline",
                    Course(
                        "",
                        "An interesting course",
                        System.currentTimeMillis() - 10000000,
                        System.currentTimeMillis(),
                        1000f,
                        "some outcome",
                        "some prerequisites",
                        "some activities",
                        "English",
                        "covered",
                        "a",
                        System.currentTimeMillis()
                    ),
                    System.currentTimeMillis()
                )
            )
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            // return empty list if error
            listOf()
        }
    }
    suspend fun addFellowship(fellowship: Fellowship): Boolean {
        return true
    }
    suspend fun updateFellowship(fellowship: Fellowship): Boolean {
        return true
    }
    suspend fun deleteFellowship(fellowship: Fellowship): Boolean {
        return true
    }
}