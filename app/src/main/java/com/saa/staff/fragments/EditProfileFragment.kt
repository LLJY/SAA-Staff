package com.saa.staff.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.saa.staff.activities.mainui.EditProfile.EditProfileViewModel
import com.saa.staff.databinding.EditProfileFragmentBinding

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

}