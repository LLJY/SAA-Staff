package com.saa.staff.activities.mainui.ViewUserInfo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.models.ParticipantUser

class ViewUserInfoViewModel @ViewModelInject constructor(private val repository: ViewUserRepository) :
    ViewModel() {
    var userUUID: String = ""
    private var user = ParticipantUser()

    fun getUser() = liveData {
        user = repository.getUserInfo(userUUID)
        emit(user)
    }
}