package com.saa.staff.interfaces

import com.saa.staff.models.Notification
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface FirebaseMessagingService {
    @Headers("Authorization: key=AAAAuMKRWFw:APA91bGQNpipmMsQRKKps9Xhfh4c2lrHFw4fZVbBbT70NSIP3-psukggekxTYJiPgUy29u_V8snmnE3IZL63QiHW1RrRdnplIlsenE5ZcBL_ncreuppsgZA5UPZrIewmTy8gO99zVedD")
    @POST("send")
    suspend fun sendNotification(@Body notification: Notification)
}