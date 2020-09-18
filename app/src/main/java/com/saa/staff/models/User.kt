package com.saa.staff.models

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.Serializable

@Serializable
data class User(
    var firstName: String = "",
    var middleName: String? = null,
    var lastName: String = "",
    var userLevel: Int = 0,
    var email: String = "",
    var passportNumber: String = "",
    var passportExpiry: Long = 0,
    var dateOfBirth: Long = 0,
    var country: String = "",
    var password: String? = "",
    var contactNumber: Int = 0
)

@Serializable
data class Course(
    var title: String,
    var startDate: Long,
    var endDate: Long,

)