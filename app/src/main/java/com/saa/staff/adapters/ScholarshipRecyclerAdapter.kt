package com.saa.staff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.databinding.CourseViewHolderBinding
import com.saa.staff.models.Scholarship
import com.saa.staff.models.ScholarshipItemDiffCallback
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

class ScholarshipRecyclerAdapter(var context: Context) :
    ListAdapter<Scholarship, ScholarshipViewHolder>(
        ScholarshipItemDiffCallback()
    ) {
    lateinit var binding: CourseViewHolderBinding
    private val detailsButtonClickPublisher = PublishSubject.create<Scholarship>()
    private val editInfoClickPublisher = PublishSubject.create<Scholarship>()
    private val deleteClickPublisher = PublishSubject.create<Scholarship>()
    val detailsButtonClick: Subject<Scholarship> get() = detailsButtonClickPublisher
    val editInfoClick: Subject<Scholarship> get() = editInfoClickPublisher
    val deleteClick: Subject<Scholarship> get() = deleteClickPublisher
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScholarshipViewHolder {
        binding = CourseViewHolderBinding.inflate(LayoutInflater.from(context), parent, false)
        return ScholarshipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScholarshipViewHolder, position: Int) {
        val scholarship = getItem(position)
        binding.durationText.text = "Bond Period: ${scholarship.bondTime}"
        binding.feesText.text = ""
        binding.languageText.text = ""
        binding.titleText.text = scholarship.title
        binding.titleText.isSelected = true
        binding.languageText.isSelected = true
        // set the onclick listener
        binding.editInfoButton.setOnClickListener {
            editInfoClickPublisher.onNext(scholarship)
        }
        binding.detailsButton.setOnClickListener {
            detailsButtonClickPublisher.onNext(scholarship)
        }
        binding.deleteButton.setOnClickListener {
            deleteClickPublisher.onNext(scholarship)
        }
    }
}

class ScholarshipViewHolder(var binding: CourseViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root)