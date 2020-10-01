package com.saa.staff.repositories

import com.saa.staff.interfaces.RetrofitService
import com.saa.staff.models.Employee
import javax.inject.Inject

class ApproveStaffRepository @Inject constructor(private val retrofit: RetrofitService) {
    suspend fun getEmployees(): List<Employee> {
        return try {
            retrofit.getUnapprovedStaff()
        } catch (ex: Exception) {
            listOf()
        }
    }

    suspend fun updateEmployees(employee: Employee): Boolean {
        return try {
            retrofit.updateEmployeeApproval(employee)
        } catch (ex: Exception) {
            false
        }
    }
}