package com.saa.staff.repositories

import android.util.Log
import com.saa.staff.interfaces.RetrofitService
import com.saa.staff.models.Scholarship
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ManageScholarshipRepository @Inject constructor(var retrofit: RetrofitService) {
    // TODO use retrofit
    suspend fun getScholarships(): List<Scholarship> {
        return try {
            retrofit.getScholarships()
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            // return empty list if error
            listOf()
        }
    }

    suspend fun addScholarship(scholarship: Scholarship): Boolean {
        return try {
            retrofit.addScholarship(scholarship)
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            false
        }
    }

    suspend fun updateScholarship(scholarship: Scholarship): Boolean {
        return try {
            retrofit.updateScholarship(scholarship)
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            false
        }
    }

    suspend fun deleteScholarship(scholarship: Scholarship): Boolean {
        return try {
            retrofit.deleteScholarship(scholarship)
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            false
        }
    }
}