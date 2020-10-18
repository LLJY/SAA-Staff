package com.saa.staff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.databinding.CourseViewHolderBinding
import com.saa.staff.models.Diploma
import com.saa.staff.models.DiplomaItemDiffCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
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
    private val detailsButtonClickPublisher = ConflatedBroadcastChannel<Diploma>()
    private val editInfoClickPublisher = ConflatedBroadcastChannel<Diploma>()
    private val deleteClickPublisher = ConflatedBroadcastChannel<Diploma>()
    val detailsButtonClick: Flow<Diploma> get() = detailsButtonClickPublisher.asFlow()
    val editInfoClick: Flow<Diploma> get() = editInfoClickPublisher.asFlow()
    val deleteClick: Flow<Diploma> get() = deleteClickPublisher.asFlow()
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
        binding.editInfoButton.setOnClickListener {
            // not sure what coroutine scope ot use, just use globalscope for now
            // let's run this on the main thread to prevent from spawning unnecessary threads and waste resources
            GlobalScope.launch(Dispatchers.Main) {
                editInfoClickPublisher.send(diploma)
            }
        }
        binding.detailsButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                detailsButtonClickPublisher.send(diploma)
            }
        }
        binding.deleteButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                deleteClickPublisher.send(diploma)
            }
        }
    }
}

class DiplomasViewHolder(var binding: CourseViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root)