package com.saa.staff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.saa.staff.databinding.ViewTrainingCourseRowLayoutBinding
import com.saa.staff.models.Scholarship
import com.saa.staff.models.ScholarshipItemDiffCallback
import io.reactivex.rxjava3.subjects.PublishSubject

class TrainingScholarshipsRecyclerAdapter constructor(var context: Context) :
    ListAdapter<Scholarship, TrainingCourseViewHolder>(
        ScholarshipItemDiffCallback()
    ) {
    private val onClickPublish = PublishSubject.create<Scholarship>()
    val onClickSubject get() = onClickPublish
    lateinit var binding: ViewTrainingCourseRowLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingCourseViewHolder {
        binding =
            ViewTrainingCourseRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return TrainingCourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrainingCourseViewHolder, position: Int) {
        binding.courseTitle.text = getItem(position).title
        binding.constraintLayout.setOnClickListener {
            onClickPublish.onNext(getItem(position))
        }
    }
}
