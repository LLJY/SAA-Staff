package com.saa.staff.models

import androidx.recyclerview.widget.DiffUtil
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
    var dateOfBirth: Long = 0,
    var country: String = "",
    var password: String? = "",
    var contactNumber: Int = 0
)

@Serializable
data class Course(
    var uuid: String,
    var title: String,
    var startDate: Long,
    var endDate: Long,
    var fees: Float,
    var learningOutcomes: String,
    var prerequisites: String,
    var learningActivities: String,
    var covered: String,
    var attending: String,
    var applicationDeadline: Long
){
    // override the toString method to correctly display in a spinner
    override fun toString(): String {
        return this.title
    }
}
class CourseItemDiffCallback : DiffUtil.ItemCallback<Course>(){
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        // only compare their unique uuids
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}

@Serializable
data class Fellowship(
    var uuid: String,
    var title: String,
    var outline: String,
    var course: Course,
    var applicationDeadline: Long
)

class FellowShipItemDiffCallback : DiffUtil.ItemCallback<Fellowship>() {
    override fun areItemsTheSame(oldItem: Fellowship, newItem: Fellowship): Boolean {
        // only compare their unique uuids
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Fellowship, newItem: Fellowship): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}

@Serializable
data class Scholarship(
    var uuid: String,
    var title: String,
    var eligibility: String,
    var benefits: String,
    var bondTime: Int,
    var outline: String
)

class ScholarshipItemDiffCallback : DiffUtil.ItemCallback<Scholarship>() {
    override fun areItemsTheSame(oldItem: Scholarship, newItem: Scholarship): Boolean {
        // only compare their unique uuids
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Scholarship, newItem: Scholarship): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}

@Serializable
data class Diploma(
    var uuid: String,
    var title: String,
    var fees: Float,
    var outline: String,
    var startDate: Long,
    var endDate: Long,
    var applicationDeadline: Long
)
class DiplomaItemDiffCallback : DiffUtil.ItemCallback<Diploma>(){
    override fun areItemsTheSame(oldItem: Diploma, newItem: Diploma): Boolean {
        // only compare their unique uuids
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Diploma, newItem: Diploma): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}

@Serializable
data class UserApplication(
    var fullName: String,
    var progressType: Int, // 0, rejected, 1 not approved, 2 in progress, 3 completed
    var applicationUUID: String,
    var user: Participant,
    var courseApplicationIndex: Int // 0 = course, 1= fellowship, 2=scholarship, 3=diploma
)

class UserApplicationDiffCallback : DiffUtil.ItemCallback<UserApplication>() {
    override fun areItemsTheSame(oldItem: UserApplication, newItem: UserApplication): Boolean {
        // compare concatenated unique user and application uuids to compare as they will surely be unique as well
        return oldItem.user.uuid + oldItem.applicationUUID == newItem.user.uuid + oldItem.applicationUUID
    }

    override fun areContentsTheSame(oldItem: UserApplication, newItem: UserApplication): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}

@Serializable
data class Participant(
    var uuid: String,
    var firstName: String,
    var lastName: String,
    var dob: Long,
    var email: String,
    var country: String,
    var passportNumber: String,
    var passportExpiry: Long,
    var organisation: String,
    var jobTitle: String,
    var password: String,
    var contactNumber: Int
)

@Serializable
data class Employee(
    var uuid: String,
    var fullName: String,
    var dob: Long,
    var contactNumber: String,
    var country: String,
    var passportNumber: String,
    var email: String,
    var userType: String,
    var approvalStatus: Int
)

class EmployeeDiffCallback : DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        // compare concatenated unique user and application uuids to compare as they will surely be unique as well
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}

@Serializable
data class Notification(val condition: String, val data: NotificationData)

@Serializable
data class NotificationData(val title: String, val message: String)

@Serializable
data class ResetPasswordModel(var email: String, var password: String)

@Serializable
data class UserInfoModel(var userId: String, var userType: Int)

