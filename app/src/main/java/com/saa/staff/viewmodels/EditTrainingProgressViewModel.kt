package com.saa.staff.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.models.UserApplication
import com.saa.staff.repositories.TrainingProgressRepository

class EditTrainingProgressViewModel @ViewModelInject constructor(private var repository: TrainingProgressRepository) :
    ViewModel() {
    var courseTypeIndex: Int = 0
    var applicants: List<UserApplication>? = null
    var courseUUID: String = ""
    fun getApplicants(courseUUID: String) = liveData {
        applicants = repository.getApplications(courseTypeIndex, courseUUID)
        emit(applicants)
    }

    fun updateApplication(userApplication: UserApplication) =
        liveData { emit(repository.updateUserApplication(userApplication)) }
}