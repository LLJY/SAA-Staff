package com.saa.staff.activities.mainui.ManageDiploma

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.models.Diploma

class ManageDiplomaViewModel @ViewModelInject constructor(var repository: ManageDiplomaRepository) :
    ViewModel() {
    var diplomas: List<Diploma>? = null
    fun getDiplomas() = liveData { emit(repository.getDiplomas()) }
    fun addDiploma(diploma: Diploma) = liveData { emit(repository.addDiploma(diploma)) }
    fun updateDiploma(diploma: Diploma) = liveData { emit(repository.updateDiploma(diploma)) }
    fun deleteDiploma(diploma: Diploma) = liveData { emit(repository.deleteDiploma(diploma)) }
}