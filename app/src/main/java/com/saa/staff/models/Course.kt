package com.saa.staff.models

import androidx.recyclerview.widget.DiffUtil
import kotlinx.serialization.Serializable

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
    var language: String,
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
        return oldItem == newItem
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
class FellowShipItemDiffCallback : DiffUtil.ItemCallback<Fellowship>(){
    override fun areItemsTheSame(oldItem: Fellowship, newItem: Fellowship): Boolean {
        // only compare their unique uuids
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: Fellowship, newItem: Fellowship): Boolean {
        return oldItem == newItem
    }

}

@Serializable
data class Diploma(
    var uuid: String,
    var title: String,
    var fees: String,
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
        return oldItem == newItem
    }

}


