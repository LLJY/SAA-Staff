package com.saa.staff.fragments

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class LoginViewModel @ViewModelInject constructor() : ViewModel() {
    public var email = ""
    public var password = ""
}