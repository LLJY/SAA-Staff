package com.saa.staff.activities.mainui.ReviewApplication

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.activities.mainui.TrainingProgress.TrainingProgressRepository
import com.saa.staff.models.UserApplication

class EditParticipantApplicationViewModel @ViewModelInject constructor(val trainingProgressRepository: TrainingProgressRepository) :
    ViewModel() {
    var courseTypeIndex: Int = 0
    fun getApplicants() =
        liveData { emit(trainingProgressRepository.getApplications(courseTypeIndex)) }

    fun updateApplication(userApplication: UserApplication) =
        liveData { emit(trainingProgressRepository.updateUserApplication(userApplication)) }
}