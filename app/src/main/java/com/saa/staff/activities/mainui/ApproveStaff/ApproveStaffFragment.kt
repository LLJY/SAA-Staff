package com.saa.staff.activities.mainui.ApproveStaff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.saa.staff.databinding.ApproveStaffFragmentBinding

class ApproveStaffFragment : Fragment() {
    val viewModel: ApproveStaffViewModel by viewModels()
    lateinit var binding: ApproveStaffFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ApproveStaffFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}