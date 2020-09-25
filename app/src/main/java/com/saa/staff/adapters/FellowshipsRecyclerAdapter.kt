package com.saa.staff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.databinding.CourseViewHolderBinding
import com.saa.staff.models.FellowShipItemDiffCallback
import com.saa.staff.models.Fellowship
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import java.text.SimpleDateFormat

class FellowshipsRecyclerAdapter(var context: Context): ListAdapter<Fellowship, FellowshipsViewHolder>(
    FellowShipItemDiffCallback()
) {
    lateinit var binding: CourseViewHolderBinding
    private val detailsButtonClickPublisher = PublishSubject.create<Fellowship>()
    private val editInfoClickPublisher = PublishSubject.create<Fellowship>()
    private val deleteClickPublisher = PublishSubject.create<Fellowship>()
    val detailsButtonClick: Subject<Fellowship> get() = detailsButtonClickPublisher
    val editInfoClick: Subject<Fellowship> get() = editInfoClickPublisher
    val deleteClick: Subject<Fellowship> get() = deleteClickPublisher
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FellowshipsViewHolder {
        binding = CourseViewHolderBinding.inflate(LayoutInflater.from(context), parent, false)
        return FellowshipsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FellowshipsViewHolder, position: Int) {
        val fellowShip = getItem(position)
        val applicationDeadline = SimpleDateFormat("dd/MM/yyyy").format(fellowShip.applicationDeadline)
        binding.durationText.text = "Application deadline: ${applicationDeadline}"
        binding.durationText.isSelected = true
        binding.feesText.text = "Course: ${fellowShip.course.title}"
        binding.languageText.text = "Language: ${fellowShip.course.language}"
        binding.titleText.text = fellowShip.title
        binding.titleText.isSelected = true
        // set the onclick listener
        binding.editInfoButton.setOnClickListener{
            editInfoClickPublisher.onNext(fellowShip)
        }
        binding.detailsButton.setOnClickListener{
            detailsButtonClickPublisher.onNext(fellowShip)
        }
        binding.deleteButton.setOnClickListener {
            deleteClickPublisher.onNext(fellowShip)
        }
    }
}

class FellowshipsViewHolder(var binding: CourseViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root)