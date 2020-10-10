package com.saa.staff.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.saa.staff.models.Participant

class ViewUserInfoViewModel @ViewModelInject constructor() :
    ViewModel() {
    lateinit var user: Participant
}