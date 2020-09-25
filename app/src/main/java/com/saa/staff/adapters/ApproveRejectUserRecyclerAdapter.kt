package com.saa.staff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.databinding.UserApproveRejectRowLayoutBinding
import com.saa.staff.models.UserApplication
import com.saa.staff.models.UserApplicationDiffCallback
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

class ApproveRejectUserRecyclerAdapter constructor(val context: Context) :
    ListAdapter<UserApplication, ApproveRejectUserViewHolder>(
        UserApplicationDiffCallback()
    ) {
    private val approvePublishSubject = PublishSubject.create<UserApplication>()
    val approveButtonClickSubject: Subject<UserApplication> get() = approvePublishSubject
    private val rejectPublishSubject = PublishSubject.create<UserApplication>()
    val rejectButtonClickSubject: Subject<UserApplication> get() = rejectPublishSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApproveRejectUserViewHolder {
        return ApproveRejectUserViewHolder(
            UserApproveRejectRowLayoutBinding.inflate(
                LayoutInflater.from(
                    context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ApproveRejectUserViewHolder, position: Int) {
        val binding = holder.binding
        val application = getItem(position)
        binding.fullNameText.text = application.fullName
        if (application.progressType == 0) {
            binding.rejectStaffButton.isEnabled = false
            binding.approveStaffButton.isEnabled = true
            binding.approveStaffButton.setOnClickListener {
                approvePublishSubject.onNext(application)
            }
        } else if (application.progressType == 2) {
            binding.approveStaffButton.isEnabled = false
            binding.rejectStaffButton.isEnabled = true
            binding.rejectStaffButton.setOnClickListener {
                rejectPublishSubject.onNext(application)
            }
        } else {
            binding.approveStaffButton.isEnabled = true
            binding.rejectStaffButton.isEnabled = true
            binding.approveStaffButton.setOnClickListener {
                approvePublishSubject.onNext(application)
            }
            binding.rejectStaffButton.setOnClickListener {
                rejectPublishSubject.onNext(application)
            }
        }


    }
}

class ApproveRejectUserViewHolder(var binding: UserApproveRejectRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root)