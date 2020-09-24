package com.saa.staff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.databinding.ViewTrainingCourseRowLayoutBinding
import com.saa.staff.models.Course
import com.saa.staff.models.CourseItemDiffCallback

class TrainingCoursesRecyclerAdapter constructor(var context: Context) :
    ListAdapter<Course, TrainingCourseViewHolder>(
        CourseItemDiffCallback()
    ) {
    lateinit var binding: ViewTrainingCourseRowLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingCourseViewHolder {
        binding =
            ViewTrainingCourseRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return TrainingCourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrainingCourseViewHolder, position: Int) {
        binding.courseTitle.text = getItem(position).title
    }

}

class TrainingCourseViewHolder(var binding: ViewTrainingCourseRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root)