package com.saa.staff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.R
import com.saa.staff.databinding.EditTrainingUsersRowLayoutBinding
import com.saa.staff.models.UserApplication
import com.saa.staff.models.UserApplicationDiffCallback
import io.reactivex.rxjava3.subjects.PublishSubject

class EditTrainingUsersRecyclerAdapter(var context: Context) :
    ListAdapter<UserApplication, EditTrainingUsersViewHolder>(
        UserApplicationDiffCallback()
    ) {
    private lateinit var binding: EditTrainingUsersRowLayoutBinding
    private var viewClickPublisher = PublishSubject.create<UserApplication>()
    val viewClickSubject get() = viewClickPublisher
    private var changeSpinnerPublisher = PublishSubject.create<UserApplication>()
    val changeSpinnerSubject get() = changeSpinnerPublisher

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditTrainingUsersViewHolder {
        binding =
            EditTrainingUsersRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return EditTrainingUsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EditTrainingUsersViewHolder, position: Int) {
        val application = getItem(position)
        // use rxjava to let activity handle onclick
        binding.root.setOnClickListener {
            viewClickPublisher.onNext(application)
        }
        val courseTypes = listOf("In Progress", "Completed")
        val arrayAdapter = ArrayAdapter(
            context,
            R.layout.support_simple_spinner_dropdown_item,
            courseTypes
        )
        (binding.statusSpinner.editText!! as AutoCompleteTextView).setAdapter(arrayAdapter)
        (binding.statusSpinner.editText!! as AutoCompleteTextView).setText(
            if (application.progressType == 2) "In Progress" else "Completed",
            false
        )
        (binding.statusSpinner.editText!! as AutoCompleteTextView).setOnItemClickListener { parent, view, position, id ->
            application.progressType = position + 2
            changeSpinnerPublisher.onNext(application)
        }
        binding.nameText.text = application.fullName
    }
}

class EditTrainingUsersViewHolder(var binding: EditTrainingUsersRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root)