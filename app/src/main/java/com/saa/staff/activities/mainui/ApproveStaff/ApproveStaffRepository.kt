package com.saa.staff.activities.mainui.ApproveStaff

import com.saa.staff.interfaces.FirebaseCloudService
import com.saa.staff.models.Employee
import javax.inject.Inject

class ApproveStaffRepository @Inject constructor(retrofit: FirebaseCloudService) {
    suspend fun getEmployees(): List<Employee> {
        return listOf(
            Employee(
                "aaa",
                "Lucas Lee",
                System.currentTimeMillis(),
                "94509747",
                "Singapore",
                "12345678",
                System.currentTimeMillis(),
                "lucasljyy@gmail.com",
                "Admin",
                1

            )
        )

    }

    suspend fun updateEmployees(uuid: String): Boolean {
        //TODO call the backend
        return true
    }
}