package com.saa.staff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.databinding.CourseViewHolderBinding
import com.saa.staff.interfaces.Disposable
import com.saa.staff.models.Course
import com.saa.staff.models.CourseItemDiffCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

class CoursesRecyclerAdapter(var context: Context): ListAdapter<Course, CoursesViewHolder>(
    CourseItemDiffCallback()
), Disposable {
    lateinit var binding: CourseViewHolderBinding
    private val detailsButtonClickPublisher = ConflatedBroadcastChannel<Course>()
    private val editInfoClickPublisher = ConflatedBroadcastChannel<Course>()
    private val deleteClickPublisher = ConflatedBroadcastChannel<Course>()
    val detailsButtonClick: Flow<Course> get() = detailsButtonClickPublisher.asFlow()
    val editInfoClick: Flow<Course> get() = editInfoClickPublisher.asFlow()
    val deleteClick: Flow<Course> get() = deleteClickPublisher.asFlow()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        binding = CourseViewHolderBinding.inflate(LayoutInflater.from(context), parent, false)
        return CoursesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        val course = getItem(position)
        val startDate = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(course.startDate),
            TimeZone.getDefault().toZoneId()
        )
        val endDate = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(course.endDate),
            TimeZone.getDefault().toZoneId()
        )
        val days = ChronoUnit.DAYS.between(startDate, endDate)
        binding.durationText.text = "Duration: $days Days"
        val formatter = NumberFormat.getCurrencyInstance()
        binding.feesText.text = "Fees: ${formatter.format(course.fees)}"
        binding.languageText.text = ""
        binding.titleText.text = course.title
        binding.titleText.isSelected = true
        // set the onclick listener
        binding.editInfoButton.setOnClickListener {
            // not sure what coroutine scope ot use, just use globalscope for now
            // let's run this on the main thread to prevent from spawning unnecessary threads and waste resources
            GlobalScope.launch(Dispatchers.Main) {
                editInfoClickPublisher.send(course)
            }
        }
        binding.detailsButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                detailsButtonClickPublisher.send(course)
            }
        }
        binding.deleteButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                deleteClickPublisher.send(course)
            }
        }
    }

}

class CoursesViewHolder(var binding: CourseViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root)