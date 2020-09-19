package com.saa.staff.activities.mainui.EditProfile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.saa.staff.R
import com.saa.staff.databinding.ApproveStaffFragmentBinding
import com.saa.staff.databinding.EditProfileFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class EditProfileFragment : Fragment() {
    val viewModel: EditProfileViewModel by viewModels()
    lateinit var binding: EditProfileFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditProfileFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}