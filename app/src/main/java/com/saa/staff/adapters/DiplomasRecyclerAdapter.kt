package com.saa.staff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.databinding.CourseViewHolderBinding
import com.saa.staff.models.Diploma
import com.saa.staff.models.DiplomaItemDiffCallback
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

class DiplomasRecyclerAdapter(var context: Context) : ListAdapter<Diploma, DiplomasViewHolder>(
    DiplomaItemDiffCallback()
) {
    lateinit var binding: CourseViewHolderBinding
    private val detailsButtonClickPublisher = PublishSubject.create<Diploma>()
    private val editInfoClickPublisher = PublishSubject.create<Diploma>()
    private val deleteClickPublisher = PublishSubject.create<Diploma>()
    val detailsButtonClick: Subject<Diploma> get() = detailsButtonClickPublisher
    val editInfoClick: Subject<Diploma> get() = editInfoClickPublisher
    val deleteClick: Subject<Diploma> get() = deleteClickPublisher
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiplomasViewHolder {
        binding = CourseViewHolderBinding.inflate(LayoutInflater.from(context), parent, false)
        return DiplomasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiplomasViewHolder, position: Int) {
        val diploma = getItem(position)
        val startDate = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(diploma.startDate),
            TimeZone.getDefault().toZoneId()
        )
        val endDate = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(diploma.endDate),
            TimeZone.getDefault().toZoneId()
        )
        val days = ChronoUnit.DAYS.between(startDate, endDate)
        binding.durationText.text = "Duration: ${days} Days"
        val formatter = NumberFormat.getCurrencyInstance()
        binding.feesText.text = "Fees: ${formatter.format(diploma.fees)}"
        val applicationDeadline = SimpleDateFormat("dd/MM/yyyy").format(diploma.applicationDeadline)
        binding.languageText.text = "Application deadline: ${applicationDeadline}"
        binding.titleText.text = diploma.title
        binding.titleText.isSelected = true
        binding.languageText.isSelected = true
        // set the onclick listener
        binding.editInfoButton.setOnClickListener{
            editInfoClickPublisher.onNext(diploma)
        }
        binding.detailsButton.setOnClickListener{
            detailsButtonClickPublisher.onNext(diploma)
        }
        binding.deleteButton.setOnClickListener {
            deleteClickPublisher.onNext(diploma)
        }
    }
}

class DiplomasViewHolder(var binding: CourseViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root)