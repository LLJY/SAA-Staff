package com.saa.staff.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.saa.staff.models.Employee
import com.saa.staff.repositories.ApproveStaffRepository

class ApproveStaffViewModel @ViewModelInject constructor(private var repository: ApproveStaffRepository) :
    ViewModel() {
    fun getEmployees() = liveData { emit(repository.getEmployees()) }
    fun updateEmployeeStatus(employee: Employee) =
        liveData { emit(repository.updateEmployees(employee)) }
}