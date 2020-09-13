package com.saa.staff.fragments

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.saa.staff.models.User
import com.saa.staff.repositories.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupViewModel @ViewModelInject constructor(private var loginRepository: LoginRepository): ViewModel() {

    public var user = User()
    fun signUp(): LiveData<Boolean>{
        return liveData (Dispatchers.IO){
            emit(loginRepository.signUp(user))
        }
    }
    fun helloWorld(){
        viewModelScope.launch {
            loginRepository.helloWorld()
        }
    }




}