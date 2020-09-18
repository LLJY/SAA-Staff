package com.saa.staff.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.repositories.LoginRepository
import kotlinx.coroutines.Dispatchers

class LoginViewModel @ViewModelInject constructor(val repository: LoginRepository) : ViewModel() {
    public var email = ""
    public var password = ""

    fun login(): LiveData<Boolean>{
        return liveData (Dispatchers.IO){
            emit(repository.login(email, password))
        }
    }
}