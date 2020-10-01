package com.saa.staff.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.repositories.SendNotificationRepository

class SendNotificationViewModel @ViewModelInject constructor(private val repository: SendNotificationRepository) :
    ViewModel() {
    fun sendNotification(title: String, message: String) =
        liveData { emit(repository.sendMessageDirect(title, message)) }
}