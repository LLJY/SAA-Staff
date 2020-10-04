package com.saa.staff.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var uuid: String = "",
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
data class ParticipantUser(
    var uuid: String = "",
    var firstName: String = "",
    var middleName: String? = null,
    var lastName: String = "",
    var organization: String = "",
    var jobTitle: String = "",
    var email: String = "",
    var passportNumber: String = "",
    var passportExpiry: Long = 0,
    var dateOfBirth: Long = 0,
    var country: String = "",
    var contactNumber: Int = 0
)