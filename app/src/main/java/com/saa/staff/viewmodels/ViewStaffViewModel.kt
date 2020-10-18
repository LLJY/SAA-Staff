package com.saa.staff.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.saa.staff.models.Employee

class ViewStaffViewModel @ViewModelInject constructor() : ViewModel() {
    lateinit var employee: Employee
}