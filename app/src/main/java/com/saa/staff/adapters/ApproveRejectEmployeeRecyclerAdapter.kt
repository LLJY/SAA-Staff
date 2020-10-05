package com.saa.staff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.saa.staff.databinding.UserApproveRejectRowLayoutBinding
import com.saa.staff.models.Employee
import com.saa.staff.models.EmployeeDiffCallback
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

class ApproveRejectEmployeeRecyclerAdapter constructor(val context: Context) :
    ListAdapter<Employee, ApproveRejectUserViewHolder>(
        EmployeeDiffCallback()
    ) {
    private val approvePublishSubject = PublishSubject.create<Employee>()
    val approveButtonClickSubject: Subject<Employee> get() = approvePublishSubject
    private val rejectPublishSubject = PublishSubject.create<Employee>()
    val rejectButtonClickSubject: Subject<Employee> get() = rejectPublishSubject
    private val viewInfoPublishSubject = PublishSubject.create<Employee>()
    val viewInfoClickSubject: Subject<Employee> get() = viewInfoPublishSubject
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
        val employee = getItem(position)
        binding.fullNameText.text = employee.fullName
        if (employee.approvalStatus == 0) {
            binding.rejectStaffButton.isEnabled = false
            binding.approveStaffButton.isEnabled = true
            binding.approveStaffButton.setOnClickListener {
                approvePublishSubject.onNext(employee)
            }
        } else if (employee.approvalStatus == 2) {
            binding.approveStaffButton.isEnabled = false
            binding.rejectStaffButton.isEnabled = true
            binding.rejectStaffButton.setOnClickListener {
                rejectPublishSubject.onNext(employee)
            }
        } else {
            binding.approveStaffButton.isEnabled = true
            binding.rejectStaffButton.isEnabled = true
            binding.approveStaffButton.setOnClickListener {
                approvePublishSubject.onNext(employee)
            }
            binding.rejectStaffButton.setOnClickListener {
                rejectPublishSubject.onNext(employee)
            }
        }
        binding.root.setOnClickListener {
            viewInfoClickSubject.onNext(employee)
        }


    }
}