package com.saa.staff.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.saa.staff.databinding.ViewScholarshipFragmentBinding
import com.saa.staff.viewmodels.ViewScholarshipViewModel

class ViewScholarshipFragment : Fragment() {
    private val viewModel: ViewScholarshipViewModel by activityViewModels()
    private lateinit var binding: ViewScholarshipFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewScholarshipFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val scholarship = viewModel.scholarship
        binding.benefitsText.text = scholarship.benefits
        binding.bondPeriodText.text = "${scholarship.bondTime} Years"
        binding.eligibilityText.text = scholarship.eligibility
        binding.title.text = scholarship.title
        binding.outlineText.text = scholarship.outline

    }

}