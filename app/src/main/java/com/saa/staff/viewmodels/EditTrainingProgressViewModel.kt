package com.saa.staff.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.activities.mainui.TrainingProgress.TrainingProgressRepository
import com.saa.staff.models.UserApplication

class EditTrainingProgressViewModel @ViewModelInject constructor(private var repository: TrainingProgressRepository) :
    ViewModel() {
    var courseTypeIndex: Int = 0
    fun getApplicants() = liveData { emit(repository.getApplications(courseTypeIndex)) }
    fun updateApplication(userApplication: UserApplication) =
        liveData { emit(repository.updateUserApplication(userApplication)) }
}