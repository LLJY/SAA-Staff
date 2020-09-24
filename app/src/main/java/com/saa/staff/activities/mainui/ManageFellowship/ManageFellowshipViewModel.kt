package com.saa.staff.activities.mainui.ManageFellowship

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.models.Fellowship

class ManageFellowshipViewModel @ViewModelInject constructor(var repository: ManageFellowshipRepository) : ViewModel() {
    var fellowships: List<Fellowship>? = null
    fun getFellowships(refresh: Boolean = false) = liveData { emit(if(fellowships == null || refresh)repository.getFellowships()else fellowships) }

    fun addFellowship(fellowShip: Fellowship): LiveData<Boolean>{
        return liveData {
            emit(repository.addFellowship(fellowShip))
        }
    }
    fun deleteFellowship(fellowShip: Fellowship): LiveData<Boolean>{
        return liveData {
            emit(repository.deleteFellowship(fellowShip))
        }
    }
    fun updateFellowship(fellowShip: Fellowship): LiveData<Boolean>{
        return liveData {
            emit(repository.updateFellowship(fellowShip))
        }
    }
}