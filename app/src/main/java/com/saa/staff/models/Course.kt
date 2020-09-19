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
    var applicationDeadline: Long
)
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
    var startDate: Long,
    var endDate: Long,
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


