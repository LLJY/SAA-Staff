package com.saa.staff.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.activities.mainui.TrainingProgress.TrainingProgressRepository
import com.saa.staff.models.UserApplication

class EditParticipantApplicationViewModel @ViewModelInject constructor(val trainingProgressRepository: TrainingProgressRepository) :
    ViewModel() {
    var courseTypeIndex: Int = 0
    var userApplications: List<UserApplication>? = null
    fun getApplicants() =
        liveData {
            userApplications = trainingProgressRepository.getApplications(courseTypeIndex)
            emit(userApplications)
        }

    fun updateApplication(userApplication: UserApplication) =
        liveData { emit(trainingProgressRepository.updateUserApplication(userApplication)) }
}