package com.saa.staff.activities.mainui.ViewUserInfo

import com.saa.staff.interfaces.RetrofitService
import com.saa.staff.models.ParticipantUser
import javax.inject.Inject

class ViewUserRepository @Inject constructor(retrofit: RetrofitService) {

    suspend fun getUserInfo(uuid: String): ParticipantUser {
        //TODO communicate with backend
        return ParticipantUser(
            "a",
            "Lucas",
            "Fokin",
            "Lee",
            "CAAS",
            "Air traffic controller",
            "lucasljyy@gmail.com",
            "12345678",
            System.currentTimeMillis(),
            System.currentTimeMillis(),
            "Singapore",
            94509747
        )
    }
}