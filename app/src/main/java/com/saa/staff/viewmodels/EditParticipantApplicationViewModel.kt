package com.saa.staff.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.models.UserApplication
import com.saa.staff.repositories.ReviewApplicationRepository
import com.saa.staff.repositories.TrainingProgressRepository

class EditParticipantApplicationViewModel @ViewModelInject constructor(
    private val repository: ReviewApplicationRepository,
    private val trainingProgressRepository: TrainingProgressRepository
) :
    ViewModel() {
    var courseTypeIndex: Int = 0
    var userApplications: List<UserApplication>? = null
    var courseUUID: String = ""
    fun getApplicants() =
        liveData {
            userApplications = repository.getApplications(courseTypeIndex, courseUUID)
            emit(userApplications)
        }

    fun updateApplication(userApplication: UserApplication) =
        liveData { emit(trainingProgressRepository.updateUserApplication(userApplication)) }
}