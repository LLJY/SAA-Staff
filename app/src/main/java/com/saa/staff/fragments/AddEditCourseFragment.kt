package com.saa.staff.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.saa.staff.databinding.AddEditCourseFragmentBinding
import com.saa.staff.viewmodels.AddEditCourseViewModel
import com.saa.staff.viewmodels.ManageCoursesViewModel
import java.text.SimpleDateFormat

class AddEditCourseFragment : Fragment() {
    private val viewModel: AddEditCourseViewModel by activityViewModels()
    val manageCourseViewModel: ManageCoursesViewModel by activityViewModels()
    private lateinit var binding: AddEditCourseFragmentBinding
    private var isExit = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddEditCourseFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isExit = false
        if(viewModel.isEdit){
            val course = viewModel.course
            binding.courseTitleText.editText!!.setText(course.title)
            binding.learningActivitiesText.editText!!.setText(course.learningActivities)
            binding.attendingText.editText!!.setText(course.attending)
            val startDate = SimpleDateFormat("dd/MM/yyyy").format(course.startDate)
            val endDate = SimpleDateFormat("dd/MM/yyyy").format(course.endDate)
            val applicationDeadline = SimpleDateFormat("dd/MM/yyyy").format(course.applicationDeadline)
            binding.courseDatePicker.editText!!.setText("$startDate to $endDate")
            binding.applicationDeadline.editText!!.setText(applicationDeadline)
            binding.feesText.editText!!.setText(course.fees.toString())
            binding.attendingText.editText!!.setText(course.attending)
            binding.prerequisitesText.editText!!.setText(course.prerequisites)
            binding.outcomeText.editText!!.setText(course.learningOutcomes)
            binding.coveredText.editText!!.setText(course.covered)
        }


        // set the datetime pickers
        binding.courseDatePicker.setEndIconOnClickListener {
            val picker = MaterialDatePicker.Builder.dateRangePicker().build()
            activity?.supportFragmentManager?.let { it1 ->
                picker.show(
                    it1,
                    picker.toString()
                )
            }
            picker.addOnPositiveButtonClickListener {
                viewModel.course.startDate = it.first!!
                viewModel.course.endDate = it.second!!
                val startDate = SimpleDateFormat("dd/MM/yyyy").format(it.first)
                val endDate = SimpleDateFormat("dd/MM/yyyy").format(it.second)
                binding.courseDatePicker.editText!!.setText("$startDate to $endDate")
            }
        }
        binding.applicationDeadline.setEndIconOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker().build()
            activity?.supportFragmentManager?.let { it1 ->
                picker.show(
                    it1,
                    picker.toString()
                )
            }
            picker.addOnPositiveButtonClickListener { date ->
                viewModel.course.applicationDeadline = date
                val dateString = SimpleDateFormat("dd/MM/yyyy").format(date)
                binding.applicationDeadline.editText!!.setText(dateString!!)
            }
        }
        binding.courseTitleText.editText!!.addTextChangedListener {
            if(it.toString().isNotBlank()){
                viewModel.course.title = it.toString()
                binding.courseTitleText.error = null
            }else{
                binding.courseTitleText.error = "Required"
            }
        }
        binding.feesText.editText!!.addTextChangedListener {
            if(it.toString().isNotBlank()){
                viewModel.course.fees = it.toString().toFloat()
                binding.feesText.error = null
            }else{
                binding.feesText.error = "Required"
            }
        }
        binding.outcomeText.editText!!.addTextChangedListener {
            if(it.toString().isNotBlank()){
                viewModel.course.learningOutcomes = it.toString()
                binding.outcomeText.error = null
            }else{
                binding.outcomeText.error = "Required"
            }
        }
        binding.attendingText.editText!!.addTextChangedListener {
            if(it.toString().isNotBlank()){
                viewModel.course.attending = it.toString()
                binding.attendingText.error = null
            }else{
                binding.attendingText.error = "Required"
            }
        }
        binding.prerequisitesText.editText!!.addTextChangedListener {
            if(it.toString().isNotBlank()){
                viewModel.course.prerequisites = it.toString()
                binding.prerequisitesText.error = null
            }else{
                binding.prerequisitesText.error = "Required"
            }
        }
        binding.learningActivitiesText.editText!!.addTextChangedListener {
            if(it.toString().isNotBlank()){
                viewModel.course.learningActivities = it.toString()
                binding.learningActivitiesText.error = null
            }else{
                binding.learningActivitiesText.error = "Required"
            }
        }
        binding.coveredText.editText!!.addTextChangedListener {
            if(it.toString().isNotBlank()){
                viewModel.course.covered = it.toString()
                binding.coveredText.error = null
            }else{
                binding.coveredText.error = "Required"
            }
        }
        binding.doneButton.setOnClickListener {
            isExit = true
            val course = viewModel.course
            if (course.applicationDeadline != 0L && course.attending.isNotBlank() && course.covered.isNotBlank() && course.startDate != 0L && course.endDate != 0L && course.learningActivities.isNotBlank() && course.learningOutcomes.isNotBlank() && course.title.isNotBlank()) {
                // check if in edit mode, then choose to update or add.
                if (viewModel.isEdit) {
                    manageCourseViewModel.updateCourse(course).observe(viewLifecycleOwner, {
                        if (it) {
                            requireActivity().onBackPressed()
                        } else {
                            Snackbar.make(
                                binding.root,
                                "Oops! Something went wrong!",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    })
                }else{
                    manageCourseViewModel.addCourse(course).observe(viewLifecycleOwner, {
                        if (it) {
                            requireActivity().onBackPressed()
                        } else {
                            Snackbar.make(
                                binding.root,
                                "Oops! Something went wrong!",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    })
                }

            }else{
                Snackbar.make(binding.root, "Ensure all fields are valid!", Snackbar.LENGTH_LONG).show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Are you sure?")
            dialog.setMessage("Are you sure you want to abandon your changes?")
            dialog.setNegativeButton("NO") { _: DialogInterface, i: Int ->

            }
            dialog.setPositiveButton("YES") { dialog: DialogInterface, i: Int ->
                dialog.dismiss()
                // disable the callback when yes is pressed and trigger onBackPressed
                this.isEnabled = false
                requireActivity().onBackPressed()
            }
            if (isExit) {
                this.isEnabled = false
                requireActivity().onBackPressed()
            } else {
                dialog.show()
            }
        }
    }

}