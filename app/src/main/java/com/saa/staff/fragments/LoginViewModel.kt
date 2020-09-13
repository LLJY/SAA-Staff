package com.saa.staff.fragments

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class LoginViewModel @ViewModelInject constructor() : ViewModel() {
    public var username = ""
    public var password = ""
}