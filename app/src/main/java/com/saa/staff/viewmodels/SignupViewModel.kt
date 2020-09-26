package com.saa.staff.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.saa.staff.models.User
import com.saa.staff.repositories.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupViewModel @ViewModelInject constructor(private var profileRepository: ProfileRepository) :
    ViewModel() {

    var user = User()
    fun signUp(): LiveData<Boolean> {
        return liveData(Dispatchers.IO) {
            emit(profileRepository.signUp(user))
        }
    }

    fun helloWorld() {
        viewModelScope.launch {
            profileRepository.helloWorld()
        }
    }




}