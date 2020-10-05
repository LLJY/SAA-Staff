package com.saa.staff.repositories

import android.util.Log
import com.saa.staff.interfaces.RetrofitService
import com.saa.staff.models.Diploma
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ManageDiplomaRepository @Inject constructor(var retrofit: RetrofitService) {
    suspend fun getDiplomas(): List<Diploma> {
        return try {
            retrofit.getDiplomas()
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            // return empty list if error
            listOf()
        }
    }

    suspend fun addDiploma(diploma: Diploma): Boolean {
        return try {
            retrofit.addDiploma(diploma)
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            // return empty list if error
            false
        }
    }

    suspend fun updateDiploma(diploma: Diploma): Boolean {
        return try {
            retrofit.updateDiploma(diploma)
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            // return empty list if error
            false
        }
    }

    suspend fun deleteDiploma(diploma: Diploma): Boolean {
        return try {
            retrofit.deleteDiploma(diploma)
        } catch (ex: Exception) {
            Log.d("Err", ex.toString())
            // return empty list if error
            false
        }
    }
}