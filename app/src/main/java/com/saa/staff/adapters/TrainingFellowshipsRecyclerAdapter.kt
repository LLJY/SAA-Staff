package com.saa.staff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.saa.staff.databinding.ViewTrainingCourseRowLayoutBinding
import com.saa.staff.models.FellowShipItemDiffCallback
import com.saa.staff.models.Fellowship

class TrainingFellowshipsRecyclerAdapter constructor(var context: Context) :
    ListAdapter<Fellowship, TrainingCourseViewHolder>(
        FellowShipItemDiffCallback()
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
