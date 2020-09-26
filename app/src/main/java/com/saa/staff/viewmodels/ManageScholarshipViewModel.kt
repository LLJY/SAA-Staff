package com.saa.staff.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.models.Scholarship
import com.saa.staff.repositories.ManageScholarshipRepository

class ManageScholarshipViewModel @ViewModelInject constructor(var repository: ManageScholarshipRepository) :
    ViewModel() {
    var scholarships: List<Scholarship>? = null

    fun getScholarships(refresh: Boolean = false) = liveData {
        if (scholarships == null || refresh) {
            emit(repository.getScholarships())
        } else {
            emit(scholarships)
        }
    }

    fun addScholarship(scholarship: Scholarship): LiveData<Boolean> {
        return liveData {
            emit(repository.addScholarship(scholarship))
        }
    }

    fun deleteScholarship(scholarship: Scholarship): LiveData<Boolean> {
        return liveData {
            emit(repository.deleteScholarship(scholarship))
        }
    }

    fun updateScholarship(scholarship: Scholarship): LiveData<Boolean> {
        return liveData {
            emit(repository.updateScholarship(scholarship))
        }
    }
}