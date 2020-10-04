package com.saa.staff.activities.mainui.ViewUserInfo

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.saa.staff.databinding.ViewUserInfoFragmentBinding
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
        pd.show()
        viewModel.getUser().observe(viewLifecycleOwner) {
            pd.dismiss()
            binding.firstNameText.text = it.firstName
            binding.middleNameText.text = it.middleName
            binding.lastNameText.text = it.lastName
            val dobString = SimpleDateFormat("dd/MM/yyyy").format(it.dateOfBirth)
            binding.dobText.text = dobString
            binding.contactNumberText.text = it.contactNumber.toString()
            binding.countryText.text = it.country
            binding.passportNumberText.text = it.passportNumber
            val passportExpiryString = SimpleDateFormat("dd/MM/yyyy").format(it.passportExpiry)
            binding.passportExpiryText.text = passportExpiryString
            binding.organizationText.text = it.organization
            binding.jobTitleText.text = it.jobTitle
            binding.emailText.text = it.email
        }

    }

}