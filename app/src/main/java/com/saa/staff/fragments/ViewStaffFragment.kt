package com.saa.staff.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.saa.staff.databinding.ViewStaffFragmentBinding
import com.saa.staff.viewmodels.ViewStaffViewModel
import java.text.SimpleDateFormat

class ViewStaffFragment : Fragment() {

    private val viewModel: ViewStaffViewModel by activityViewModels()
    private lateinit var binding: ViewStaffFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewStaffFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fullNameText.text = viewModel.employee.fullName
        binding.contactNumberText.text = viewModel.employee.contactNumber
        binding.countryText.text = viewModel.employee.country
        binding.emailText.text = viewModel.employee.email
        binding.jobTitleText.text = viewModel.employee.jobTitle
        binding.dobText.text = SimpleDateFormat("dd/MM/yyyy").format(viewModel.employee.dob)
        binding.passportNumberText.text = viewModel.employee.passportNumber
    }

}