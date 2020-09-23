package com.saa.staff.activities.mainui.ManageFellowship

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.saa.staff.R
import com.saa.staff.activities.mainui.ManageCourses.ManageCoursesViewModel
import com.saa.staff.databinding.AddEditFellowshipFragmentBinding
import com.saa.staff.models.Course
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class AddEditFellowshipFragment : Fragment() {
    private val viewModel: AddEditFellowshipViewModel by activityViewModels()
    private val manageFellowShipViewModel: ManageFellowshipViewModel by activityViewModels()
    @Inject lateinit var pd: ProgressDialog
    // inject the viewmodel
    private val manageCourseViewModel: ManageCoursesViewModel by activityViewModels()
    lateinit var binding: AddEditFellowshipFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddEditFellowshipFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pd.show()
        manageCourseViewModel.getCourses().observe(viewLifecycleOwner) {
            pd.dismiss()
            viewModel.courseList = it as ArrayList<Course>
            val adapter =
                ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, it)
            (binding.courseSpinner.editText!! as AutoCompleteTextView).setAdapter(adapter)
            (binding.courseSpinner.editText!! as AutoCompleteTextView).setOnItemClickListener { parent, view, position, id ->
                viewModel.fellowShip.course = viewModel.courseList[position]
            }
            if (viewModel.isEdit) {
                val courseIndex = viewModel.courseList.indexOf(viewModel.fellowShip.course)
                // ensure it does not get set to -1
                (binding.courseSpinner.editText!! as AutoCompleteTextView).setSelection(if(courseIndex == -1) 0 else courseIndex)
            }
        }
        // set up the fields if is editing
        if(viewModel.isEdit){
            binding.courseTitleText.editText!!.setText(viewModel.fellowShip.title)
            binding.outlineText.editText!!.setText(viewModel.fellowShip.outline)
            val date = SimpleDateFormat("dd/MM/yyyy").format(viewModel.fellowShip.applicationDeadline)
            binding.applicationDeadline.editText!!.setText(date)
        }

        binding.courseTitleText.editText!!.addTextChangedListener {
            viewModel.fellowShip.title = it.toString()
        }
        binding.outlineText.editText!!.addTextChangedListener {
            viewModel.fellowShip.outline = it.toString()
        }
        binding.applicationDeadline.setEndIconOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker().build()
            activity?.supportFragmentManager?.let { it1 ->
                picker.show(
                    it1,
                    picker.toString()
                )
            }
            picker.addOnPositiveButtonClickListener {
                viewModel.fellowShip.applicationDeadline = it!!
                val date = SimpleDateFormat("dd/MM/yyyy").format(it)
                binding.applicationDeadline.editText!!.setText(date)
            }
        }
        binding.doneButton.setOnClickListener {
            val fellowShip = viewModel.fellowShip
            if(fellowShip.applicationDeadline != 0L && fellowShip.course.title.isNotBlank() && fellowShip.outline.isNotBlank() && fellowShip.title.isNotBlank()){
                // if it is editing call update, or else, call
                pd.show()
                if(viewModel.isEdit){
                    Snackbar.make(binding.root, "updating fellowship...", Snackbar.LENGTH_LONG).show()
                    manageFellowShipViewModel.updateFellowship(fellowShip).observe(viewLifecycleOwner){
                        pd.dismiss()
                    }
                }else{
                    Snackbar.make(binding.root, "adding fellowship...", Snackbar.LENGTH_LONG).show()
                    manageFellowShipViewModel.addFellowship(fellowShip).observe(viewLifecycleOwner){
                        pd.dismiss()
                    }
                }
            }else{
                Snackbar.make(binding.root, "Ensure all fields are valid!", Snackbar.LENGTH_LONG).show()
            }
        }
    }

}