package com.saa.staff.repositories

import android.util.Log
import com.saa.staff.interfaces.FirebaseCloudService
import com.saa.staff.models.Fellowship
import javax.inject.Inject

class ManageFellowshipRepository @Inject constructor(var retrofit: FirebaseCloudService) {
    suspend fun getFellowships(): List<Fellowship> {
        return try {
            retrofit.getFellowships()
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            // return empty list if error
            listOf()
        }
    }
    suspend fun addFellowship(fellowship: Fellowship): Boolean {
        return try {
            retrofit.addFellowship(fellowship)
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            false
        }
    }
    suspend fun updateFellowship(fellowship: Fellowship): Boolean {
        return try {
            retrofit.updateFellowship(fellowship)
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            false
        }
    }
    suspend fun deleteFellowship(fellowship: Fellowship): Boolean {
        return try {
            retrofit.deleteFellowship(fellowship)
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            false
        }
    }
}