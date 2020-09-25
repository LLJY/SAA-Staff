package com.saa.staff.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.models.Diploma
import com.saa.staff.repositories.ManageDiplomaRepository

class ManageDiplomaViewModel @ViewModelInject constructor(var repository: ManageDiplomaRepository) :
    ViewModel() {
    var diplomas: List<Diploma>? = null
    fun getDiplomas(refresh: Boolean = false) =
        liveData { emit(if (diplomas == null || refresh) repository.getDiplomas() else diplomas) }

    fun addDiploma(diploma: Diploma) = liveData { emit(repository.addDiploma(diploma)) }
    fun updateDiploma(diploma: Diploma) = liveData { emit(repository.updateDiploma(diploma)) }
    fun deleteDiploma(diploma: Diploma) = liveData { emit(repository.deleteDiploma(diploma)) }
}