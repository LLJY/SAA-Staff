package com.saa.staff.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.repositories.ProfileRepository

class ForgotPasswordViewModel @ViewModelInject constructor(private val repository: ProfileRepository) :
    ViewModel() {
    var email: String = ""
    var password: String = ""

    fun resetPassword() = liveData { emit(repository.resetPassword(email, password)) }

}