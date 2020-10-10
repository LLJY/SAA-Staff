package com.saa.staff.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.saa.staff.databinding.ViewUserInfoFragmentBinding
import com.saa.staff.viewmodels.ViewUserInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class ViewUserInfoFragment : Fragment() {
    private val viewModel: ViewUserInfoViewModel by activityViewModels()
    private lateinit var binding: ViewUserInfoFragmentBinding

    @Inject
    lateinit var pd: ProgressDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewUserInfoFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = viewModel.user
        binding.firstNameText.text = user.firstName
        binding.middleNameText.isVisible = false
        binding.lastNameText.text = user.lastName
        val dobString = SimpleDateFormat("dd/MM/yyyy").format(user.dob)
        binding.dobText.text = dobString
        binding.contactNumberText.text = user.contactNumber.toString()
        binding.countryText.text = user.country
        binding.passportNumberText.text = user.passportNumber
        val passportExpiryString = SimpleDateFormat("dd/MM/yyyy").format(user.passportExpiry)
        binding.passportExpiryText.text = passportExpiryString
        binding.organizationText.text = user.organisation
        binding.jobTitleText.text = user.jobTitle
        binding.emailText.text = user.email
    }

}