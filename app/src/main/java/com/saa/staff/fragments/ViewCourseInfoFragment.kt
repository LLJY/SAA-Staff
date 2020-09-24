package com.saa.staff.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.saa.staff.databinding.ViewCourseInfoFragmentBinding
import com.saa.staff.viewModels.ViewCourseInfoViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat

class ViewCourseInfoFragment : Fragment() {
    val viewModel: ViewCourseInfoViewModel by activityViewModels()
    lateinit var binding: ViewCourseInfoFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewCourseInfoFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val course = viewModel.course
        binding.title.text = course.title
        binding.attendText.text = course.attending
        val startDate = SimpleDateFormat("dd/MM/yyyy").format(course.startDate)
        val endDate = SimpleDateFormat("dd/MM/yyyy").format(course.endDate)
        binding.startDateText.text = startDate
        binding.endDateText.text = endDate
        val formatter = NumberFormat.getCurrencyInstance()
        binding.feesText.text = "Fees: ${formatter.format(course.fees)}"
        binding.learningOutcomesText.text = course.learningOutcomes
        binding.attendText.text = course.attending
        binding.prerequisitesText.text = course.prerequisites
        binding.languageText.text = course.language
        binding.coveredText.text = course.covered

    }

}