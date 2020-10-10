package com.saa.staff.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.activities.mainui.ReviewApplication.ReviewApplicationRepository
import com.saa.staff.activities.mainui.TrainingProgress.TrainingProgressRepository
import com.saa.staff.models.UserApplication

class EditParticipantApplicationViewModel @ViewModelInject constructor(
    private val repository: ReviewApplicationRepository,
    private val trainingProgressRepository: TrainingProgressRepository
) :
    ViewModel() {
    var courseTypeIndex: Int = 0
    var userApplications: List<UserApplication>? = null
    fun getApplicants() =
        liveData {
            userApplications = repository.getApplications(courseTypeIndex)
            emit(userApplications)
        }

    fun updateApplication(userApplication: UserApplication) =
        liveData { emit(trainingProgressRepository.updateUserApplication(userApplication)) }
}