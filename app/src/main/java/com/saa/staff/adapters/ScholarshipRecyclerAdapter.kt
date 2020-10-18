package com.saa.staff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.databinding.CourseViewHolderBinding
import com.saa.staff.models.Scholarship
import com.saa.staff.models.ScholarshipItemDiffCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch

class ScholarshipRecyclerAdapter(var context: Context) :
    ListAdapter<Scholarship, ScholarshipViewHolder>(
        ScholarshipItemDiffCallback()
    ) {
    lateinit var binding: CourseViewHolderBinding
    private val detailsButtonClickPublisher = ConflatedBroadcastChannel<Scholarship>()
    private val editInfoClickPublisher = ConflatedBroadcastChannel<Scholarship>()
    private val deleteClickPublisher = ConflatedBroadcastChannel<Scholarship>()
    val detailsButtonClick: Flow<Scholarship> get() = detailsButtonClickPublisher.asFlow()
    val editInfoClick: Flow<Scholarship> get() = editInfoClickPublisher.asFlow()
    val deleteClick: Flow<Scholarship> get() = deleteClickPublisher.asFlow()
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
            // not sure what coroutine scope ot use, just use globalscope for now
            // let's run this on the main thread to prevent from spawning unnecessary threads and waste resources
            GlobalScope.launch(Dispatchers.Main) {
                editInfoClickPublisher.send(scholarship)
            }
        }
        binding.detailsButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                detailsButtonClickPublisher.send(scholarship)
            }
        }
        binding.deleteButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                deleteClickPublisher.send(scholarship)
            }
        }
    }
}

class ScholarshipViewHolder(var binding: CourseViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root)