package com.saa.staff.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.saa.staff.databinding.ViewDiplomaFragmentBinding
import com.saa.staff.viewmodels.ViewDiplomaViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat

class ViewDiplomaFragment : Fragment() {

    private val viewModel: ViewDiplomaViewModel by activityViewModels()
    private lateinit var binding: ViewDiplomaFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewDiplomaFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.applicationDeadlineTitle.text =
            SimpleDateFormat("dd/MM/yyyy").format(viewModel.diploma.applicationDeadline)
        val startDateString = SimpleDateFormat("dd/MM/yyyy").format(viewModel.diploma.startDate)
        val endDateString = SimpleDateFormat("dd/MM/yyyy").format(viewModel.diploma.endDate)
        binding.datesText.text = "$startDateString to $endDateString"
        val formatter = NumberFormat.getCurrencyInstance()
        binding.feesText.text = formatter.format(viewModel.diploma.fees)
        binding.title.text = viewModel.diploma.title
    }

}