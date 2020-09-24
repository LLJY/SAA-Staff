package com.saa.staff.activities.mainui.TrainingProgress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.saa.staff.R
import com.saa.staff.adapters.TrainingCoursesRecyclerAdapter
import com.saa.staff.adapters.TrainingDiplomasRecyclerAdapter
import com.saa.staff.adapters.TrainingFellowshipsRecyclerAdapter
import com.saa.staff.adapters.TrainingScholarshipsRecyclerAdapter
import com.saa.staff.databinding.TrainingProgressFragmentBinding
import com.saa.staff.viewModels.ManageCoursesViewModel
import com.saa.staff.viewModels.ManageDiplomaViewModel
import com.saa.staff.viewModels.ManageFellowshipViewModel
import com.saa.staff.viewModels.ManageScholarshipViewModel

class TrainingProgressFragment : Fragment() {
    val viewModel: TrainingProgressViewModel by viewModels()
    val manageCoursesViewModel: ManageCoursesViewModel by activityViewModels()
    val manageDiplomaViewModel: ManageDiplomaViewModel by activityViewModels()
    val manageScholarshipViewModel: ManageScholarshipViewModel by activityViewModels()
    val manageFellowshipViewModel: ManageFellowshipViewModel by activityViewModels()
    lateinit var binding: TrainingProgressFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TrainingProgressFragmentBinding.inflate(layoutInflater)
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
                }
                1 -> {
                    val adapter = TrainingFellowshipsRecyclerAdapter(requireContext())
                    binding.recycler.adapter = adapter
                    manageFellowshipViewModel.getFellowships().observe(viewLifecycleOwner) {
                        adapter.submitList(it)
                    }
                }
                2 -> {
                    val adapter = TrainingScholarshipsRecyclerAdapter(requireContext())
                    binding.recycler.adapter = adapter
                    manageScholarshipViewModel.getScholarships().observe(viewLifecycleOwner) {
                        adapter.submitList(it)
                    }
                }
                3 -> {
                    val adapter = TrainingDiplomasRecyclerAdapter(requireContext())
                    binding.recycler.adapter = adapter
                    manageDiplomaViewModel.getDiplomas().observe(viewLifecycleOwner) {
                        adapter.submitList(it)
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
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }
}