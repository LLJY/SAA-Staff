package com.saa.staff.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.saa.staff.R
import com.saa.staff.adapters.TrainingCoursesRecyclerAdapter
import com.saa.staff.adapters.TrainingDiplomasRecyclerAdapter
import com.saa.staff.adapters.TrainingFellowshipsRecyclerAdapter
import com.saa.staff.adapters.TrainingScholarshipsRecyclerAdapter
import com.saa.staff.databinding.ReviewApplicationFragmentBinding
import com.saa.staff.viewmodels.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewApplicationFragment : Fragment() {
    val viewModel: ReviewApplicationViewModel by viewModels()
    val manageCoursesViewModel: ManageCoursesViewModel by activityViewModels()
    val manageDiplomaViewModel: ManageDiplomaViewModel by activityViewModels()
    val manageScholarshipViewModel: ManageScholarshipViewModel by activityViewModels()
    val manageFellowshipViewModel: ManageFellowshipViewModel by activityViewModels()
    val editParticipantApplicationViewModel: EditParticipantApplicationViewModel by activityViewModels()
    lateinit var binding: ReviewApplicationFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ReviewApplicationFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val courseTypes = listOf("Courses", "Fellowships", "Scholarships", "Diplomas")
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            courseTypes
        )
        (binding.typeSpinner.editText!! as AutoCompleteTextView).setAdapter(arrayAdapter)
        (binding.typeSpinner.editText!! as AutoCompleteTextView).setOnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    val adapter = TrainingCoursesRecyclerAdapter(requireContext())
                    binding.recycler.adapter = adapter
                    manageCoursesViewModel.getCourses().observe(viewLifecycleOwner) {
                        adapter.submitList(it)
                    }
                    adapter.onClickSubject.subscribe {
                        editParticipantApplicationViewModel.courseTypeIndex = 0
                        findNavController().navigate(ReviewApplicationFragmentDirections.actionReviewApplicationFragmentToEditParticipantApplicationFragment())
                    }
                }
                1 -> {
                    val adapter = TrainingFellowshipsRecyclerAdapter(requireContext())
                    binding.recycler.adapter = adapter
                    manageFellowshipViewModel.getFellowships().observe(viewLifecycleOwner) {
                        adapter.submitList(it)
                    }
                    adapter.onClickSubject.subscribe {
                        editParticipantApplicationViewModel.courseTypeIndex = 1
                        findNavController().navigate(ReviewApplicationFragmentDirections.actionReviewApplicationFragmentToEditParticipantApplicationFragment())
                    }
                }
                2 -> {
                    val adapter = TrainingScholarshipsRecyclerAdapter(requireContext())
                    binding.recycler.adapter = adapter
                    manageScholarshipViewModel.getScholarships().observe(viewLifecycleOwner) {
                        adapter.submitList(it)
                    }
                    adapter.onClickSubject.subscribe {
                        editParticipantApplicationViewModel.courseTypeIndex = 2
                        findNavController().navigate(ReviewApplicationFragmentDirections.actionReviewApplicationFragmentToEditParticipantApplicationFragment())
                    }
                }
                3 -> {
                    val adapter = TrainingDiplomasRecyclerAdapter(requireContext())
                    binding.recycler.adapter = adapter
                    manageDiplomaViewModel.getDiplomas().observe(viewLifecycleOwner) {
                        adapter.submitList(it)
                    }
                    adapter.onClickSubject.subscribe {
                        editParticipantApplicationViewModel.courseTypeIndex = 3
                        findNavController().navigate(ReviewApplicationFragmentDirections.actionReviewApplicationFragmentToEditParticipantApplicationFragment())
                    }
                }
            }
            binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        }
        // set the default selection as courses
        (binding.typeSpinner.editText!! as AutoCompleteTextView).setText("Courses", false)
        val adapter = TrainingCoursesRecyclerAdapter(requireContext())
        binding.recycler.adapter = adapter
        manageCoursesViewModel.getCourses().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        adapter.onClickSubject.subscribe {
            editParticipantApplicationViewModel.courseTypeIndex = 0
            findNavController().navigate(ReviewApplicationFragmentDirections.actionReviewApplicationFragmentToEditParticipantApplicationFragment())
        }
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }

}