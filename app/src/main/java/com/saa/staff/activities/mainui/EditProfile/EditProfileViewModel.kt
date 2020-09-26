package com.saa.staff.activities.mainui.EditProfile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.models.User
import com.saa.staff.repositories.ProfileRepository
import kotlinx.coroutines.Dispatchers

class EditProfileViewModel @ViewModelInject constructor(val profileRepository: ProfileRepository) :
    ViewModel() {
    var user = User()
    fun updateUser(): LiveData<Boolean> {
        return liveData(Dispatchers.IO) {
            emit(profileRepository.updateUser(user))
        }
    }

    /**
     * get the user info and the view should set user inside viewModel
     */
    fun getUserInfo(uuid: String) =
        // return the already stored user.
        liveData { emit(if (user.uuid.isBlank()) profileRepository.getUserInfo(uuid) else user) }

    /**
     *  just resets the user, used when update has finished.
     */
    fun clearViewModel() {
        user = User()
    }

}