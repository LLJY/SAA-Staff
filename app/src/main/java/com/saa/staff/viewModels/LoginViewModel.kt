package com.saa.staff.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.repositories.ProfileRepository
import kotlinx.coroutines.Dispatchers

class LoginViewModel @ViewModelInject constructor(val repository: ProfileRepository) : ViewModel() {
    var email = ""
    var password = ""

    fun login() = liveData(Dispatchers.IO) { emit(repository.login(email, password)) }
}