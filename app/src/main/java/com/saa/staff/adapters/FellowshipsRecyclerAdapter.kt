package com.saa.staff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.databinding.CourseViewHolderBinding
import com.saa.staff.models.FellowShipItemDiffCallback
import com.saa.staff.models.Fellowship
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class FellowshipsRecyclerAdapter(var context: Context): ListAdapter<Fellowship, FellowshipsViewHolder>(
    FellowShipItemDiffCallback()
) {
    lateinit var binding: CourseViewHolderBinding
    private val detailsButtonClickPublisher = ConflatedBroadcastChannel<Fellowship>()
    private val editInfoClickPublisher = ConflatedBroadcastChannel<Fellowship>()
    private val deleteClickPublisher = ConflatedBroadcastChannel<Fellowship>()
    val detailsButtonClick: Flow<Fellowship> get() = detailsButtonClickPublisher.asFlow()
    val editInfoClick: Flow<Fellowship> get() = editInfoClickPublisher.asFlow()
    val deleteClick: Flow<Fellowship> get() = deleteClickPublisher.asFlow()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FellowshipsViewHolder {
        binding = CourseViewHolderBinding.inflate(LayoutInflater.from(context), parent, false)
        return FellowshipsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FellowshipsViewHolder, position: Int) {
        val fellowShip = getItem(position)
        val applicationDeadline =
            SimpleDateFormat("dd/MM/yyyy").format(fellowShip.applicationDeadline)
        binding.durationText.text = "Application deadline: ${applicationDeadline}"
        binding.durationText.isSelected = true
        binding.feesText.text = "Course: ${fellowShip.course.title}"
        binding.languageText.text = ""
        binding.titleText.text = fellowShip.title
        binding.titleText.isSelected = true
        // set the onclick listener
        binding.editInfoButton.setOnClickListener {
            // not sure what coroutine scope ot use, just use globalscope for now
            // let's run this on the main thread to prevent from spawning unnecessary threads and waste resources
            GlobalScope.launch(Dispatchers.Main) {
                editInfoClickPublisher.send(fellowShip)
            }
        }
        binding.detailsButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                detailsButtonClickPublisher.send(fellowShip)
            }
        }
        binding.deleteButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                deleteClickPublisher.send(fellowShip)
            }
        }
    }
}

class FellowshipsViewHolder(var binding: CourseViewHolderBinding) :
    RecyclerView.ViewHolder(binding.root)