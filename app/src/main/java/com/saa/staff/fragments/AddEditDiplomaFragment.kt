package com.saa.staff.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.saa.staff.databinding.AddEditDiplomaFragmentBinding
import com.saa.staff.viewModels.AddEditDiplomaViewModel
import com.saa.staff.viewModels.ManageDiplomaViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import javax.inject.Inject

@AndroidEntryPoint
class AddEditDiplomaFragment : Fragment() {
    private val viewModel: AddEditDiplomaViewModel by activityViewModels()
    val manageDiplomaViewModel: ManageDiplomaViewModel by activityViewModels()
    private lateinit var binding: AddEditDiplomaFragmentBinding
    @Inject
    lateinit var pd: ProgressDialog
    private var isExit = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddEditDiplomaFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isExit = false
        // if in edit mode, fill up all the textboxes
        if (viewModel.isEdit) {
            val diploma = viewModel.diploma
            binding.outlineText.editText!!.setText(diploma.outline)
            binding.feesText.editText!!.setText(diploma.fees.toString())
            binding.diplomaTitleText.editText!!.setText(diploma.title)
            val startDateString = SimpleDateFormat("dd/MM/yyyy").format(viewModel.diploma.startDate)
            val endDateString = SimpleDateFormat("dd/MM/yyyy").format(viewModel.diploma.endDate)
            binding.diplomaDurationPicker.editText!!.setText("$startDateString to $endDateString")
            val applicationDeadlineString =
                SimpleDateFormat("dd/MM/yyyy").format(viewModel.diploma.applicationDeadline)
            binding.applicationDeadlinePicker.editText!!.setText(applicationDeadlineString)
        }
        binding.applicationDeadlinePicker.setEndIconOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker().build()
            activity?.supportFragmentManager?.let { it1 ->
                picker.show(
                    it1,
                    picker.toString()
                )
            }
            picker.addOnPositiveButtonClickListener {
                viewModel.diploma.applicationDeadline = it!!
                val date = SimpleDateFormat("dd/MM/yyyy").format(it)
                binding.applicationDeadlinePicker.editText!!.setText(date)
            }
        }
        binding.diplomaDurationPicker.setEndIconOnClickListener {
            val picker = MaterialDatePicker.Builder.dateRangePicker().build()
            activity?.supportFragmentManager?.let { it1 ->
                picker.show(
                    it1,
                    picker.toString()
                )
            }
            picker.addOnPositiveButtonClickListener {
                viewModel.diploma.startDate = it.first!!
                viewModel.diploma.endDate = it.second!!
                val startDateString = SimpleDateFormat("dd/MM/yyyy").format(it.first)
                val endDateString = SimpleDateFormat("dd/MM/yyyy").format(it.second)
                binding.diplomaDurationPicker.editText!!.setText("$startDateString to $endDateString")
            }
        }
        binding.diplomaTitleText.editText!!.addTextChangedListener {
            if (!it.isNullOrBlank()) {
                binding.diplomaTitleText.error = null
                viewModel.diploma.title = it.toString()
            } else {
                binding.diplomaTitleText.error = "Required"
            }
        }
        binding.feesText.editText!!.addTextChangedListener {
            if (!it.isNullOrBlank()) {
                binding.feesText.error = null
                viewModel.diploma.fees = it.toString().toFloat()
            } else {
                binding.feesText.error = "Required"
            }
        }
        binding.outlineText.editText!!.addTextChangedListener {
            if (!it.isNullOrBlank()) {
                binding.outlineText.error = null
                viewModel.diploma.outline = it.toString()
            } else {
                binding.outlineText.error = "Required"
            }
        }
        binding.doneButton.setOnClickListener {
            val diploma = viewModel.diploma
            if (diploma.applicationDeadline != 0L && diploma.endDate != 0L && diploma.startDate != 0L && diploma.fees != 0f && diploma.outline.isNotBlank() && diploma.title.isNotBlank()) {
                // if valid, figure out to edit or delete
                pd.show()
                if (viewModel.isEdit) {
                    manageDiplomaViewModel.updateDiploma(diploma).observe(viewLifecycleOwner) {
                        pd.dismiss()
                        if (it) {
                            isExit = true
                            requireActivity().onBackPressed()
                        } else {
                            Snackbar.make(
                                binding.root,
                                "Oops! Something went wrong",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {
                    manageDiplomaViewModel.addDiploma(diploma).observe(viewLifecycleOwner) {
                        pd.dismiss()
                        if (it) {
                            isExit = true
                            requireActivity().onBackPressed()
                        } else {
                            Snackbar.make(
                                binding.root,
                                "Oops! Something went wrong",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            } else {
                Snackbar.make(binding.root, "Ensure all fields are valid!", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

}