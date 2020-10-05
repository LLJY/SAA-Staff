package com.saa.staff.repositories

import android.util.Log
import com.saa.staff.interfaces.FirebaseMessagingService
import com.saa.staff.models.Notification
import com.saa.staff.models.NotificationData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SendNotificationRepository @Inject constructor(private val retrofit: FirebaseMessagingService) {
    suspend fun sendMessageDirect(title: String, message: String): Boolean {
        return try {
            retrofit.sendNotification(
                Notification(
                    "\'topic\' in topics",
                    NotificationData(
                        title,
                        message
                    )
                )
            )
            true
        } catch (ex: Exception) {
            Log.d("send notification repository", ex.toString())
            false
        }
    }
}