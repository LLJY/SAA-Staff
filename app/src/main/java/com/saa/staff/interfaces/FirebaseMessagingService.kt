package com.saa.staff.interfaces

import com.saa.staff.models.Notification
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FirebaseMessagingService {
    @Headers("Authorization : key=")
    @POST("send")
    suspend fun sendNotification(@Body notification: Notification)
}