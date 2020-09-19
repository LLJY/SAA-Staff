package com.saa.staff.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.databinding.CourseViewHolderBinding
import com.saa.staff.interfaces.Disposable
import com.saa.staff.models.Course
import com.saa.staff.models.CourseItemDiffCallback
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import java.text.NumberFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

class CoursesRecyclerAdapter(var context: Context): ListAdapter<Course, CoursesViewHolder>(
    CourseItemDiffCallback()
), Disposable {
    lateinit var binding: CourseViewHolderBinding
    private val detailsButtonClickPublisher = PublishSubject.create<Course>()
    private val editInfoClickPublisher = PublishSubject.create<Course>()
    private val deleteClickPublisher = PublishSubject.create<Course>()
    val detailsButtonClick: Subject<Course> get() = detailsButtonClickPublisher
    val editInfoClick: Subject<Course> get() = editInfoClickPublisher
    val deleteClick: Subject<Course> get() = deleteClickPublisher
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        binding = CourseViewHolderBinding.inflate(LayoutInflater.from(context), parent, false);
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
        binding.durationText.text = "Duration: ${days.toString()} Days"
        val formatter = NumberFormat.getCurrencyInstance()
        binding.feesText.text = "Fees: ${formatter.format(course.fees)}"
        binding.languageText.text = "Language: ${course.language}"
        binding.titleText.text = course.title
        // set the onclick listener
        binding.editInfoButton.setOnClickListener{
            editInfoClickPublisher.onNext(course)
        }
        binding.detailsButton.setOnClickListener{
            detailsButtonClickPublisher.onNext(course)
        }
        binding.deleteButton.setOnClickListener {
            deleteClickPublisher.onNext(course)
        }
    }

    override fun dispose() {
        super.dispose()
    }
}

class CoursesViewHolder(var binding: CourseViewHolderBinding): RecyclerView.ViewHolder(binding.root){
}