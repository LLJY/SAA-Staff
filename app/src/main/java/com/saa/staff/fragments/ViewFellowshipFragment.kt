package com.saa.staff.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.saa.staff.databinding.ViewFellowshipFragmentBinding
import com.saa.staff.viewmodels.ViewFellowshipViewModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

class ViewFellowshipFragment : Fragment() {
    private val viewModel: ViewFellowshipViewModel by activityViewModels()
    private lateinit var binding: ViewFellowshipFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewFellowshipFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val fellowShip = viewModel.fellowShip
        binding.applicationDeadlineText.text = SimpleDateFormat("dd/MM/yyyy").format(fellowShip.applicationDeadline)
        binding.courseText.text = fellowShip.course.title
        val startDate = SimpleDateFormat("dd/MM/yyyy").format(fellowShip.course.startDate)
        val endDate = SimpleDateFormat("dd/MM/yyyy").format(fellowShip.course.endDate)
        binding.datesText.text = "$startDate to $endDate"
        val applicationDeadline = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(fellowShip.applicationDeadline),
            TimeZone.getDefault().toZoneId())
        binding.statusText.text = if(applicationDeadline.isBefore(LocalDateTime.now())) "Open" else "Closed"
        binding.outlineText.text = fellowShip.outline
        binding.title.text = fellowShip.title
    }

}