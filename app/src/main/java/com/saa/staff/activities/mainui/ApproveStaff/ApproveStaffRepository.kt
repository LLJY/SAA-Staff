package com.saa.staff.activities.mainui.ApproveStaff

import com.saa.staff.interfaces.FirebaseCloudService
import com.saa.staff.models.Employee
import javax.inject.Inject

class ApproveStaffRepository @Inject constructor(private val retrofit: FirebaseCloudService) {
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