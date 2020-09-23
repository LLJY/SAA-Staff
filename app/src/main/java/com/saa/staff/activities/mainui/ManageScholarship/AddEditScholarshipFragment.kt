package com.saa.staff.activities.mainui.ManageScholarship

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.saa.staff.databinding.AddEditScholarshipFragmentBinding
import javax.inject.Inject

class AddEditScholarshipFragment : Fragment() {
    private var isExit = false
    private val viewModel: AddEditScholarshipViewModel by activityViewModels()
    private val manageScholarshipViewModel: ManageScholarshipViewModel by activityViewModels()
    private lateinit var binding: AddEditScholarshipFragmentBinding

    @Inject
    lateinit var pd: ProgressDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddEditScholarshipFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (viewModel.isEdit) {
            val scholarship = viewModel.scholarship
            binding.titleText.editText!!.setText(scholarship.title)
            binding.benefitsText.editText!!.setText(scholarship.benefits)
            binding.eligibilityText.editText!!.setText(scholarship.eligibility)
            binding.outlineText.editText!!.setText(scholarship.outline)
        }
        binding.titleText.editText!!.addTextChangedListener {
            if (it.toString().isNotBlank()) {
                binding.titleText.error = null
                viewModel.scholarship.title = it.toString()
            } else {
                binding.titleText.error = "Required"
            }
        }
        binding.outlineText.editText!!.addTextChangedListener {
            if (it.toString().isNotBlank()) {
                binding.outlineText.error = null
                viewModel.scholarship.outline = it.toString()
            } else {
                binding.outlineText.error = "Required"
            }
        }
        binding.eligibilityText.editText!!.addTextChangedListener {
            if (it.toString().isNotBlank()) {
                binding.eligibilityText.error = null
                viewModel.scholarship.eligibility = it.toString()
            } else {
                binding.eligibilityText.error = "Required"
            }
        }
        binding.benefitsText.editText!!.addTextChangedListener {
            if (it.toString().isNotBlank()) {
                binding.benefitsText.error = null
                viewModel.scholarship.benefits = it.toString()
            } else {
                binding.benefitsText.error = "Required"
            }
        }
        binding.bondPeriodText.editText!!.addTextChangedListener {
            if (it.toString().isNotBlank()) {
                binding.bondPeriodText.error = null
                viewModel.scholarship.bondTime = it.toString().toInt()
            } else {
                binding.bondPeriodText.error = "Required"
            }
        }
        binding.doneButton.setOnClickListener {
            val scholarship = viewModel.scholarship
            if (scholarship.benefits.isNotBlank() && scholarship.bondTime != 0 && scholarship.eligibility.isNotBlank() && scholarship.outline.isNotBlank() && scholarship.title.isNotBlank()) {
                pd.show()
                if (viewModel.isEdit) {
                    manageScholarshipViewModel.updateScholarship(scholarship)
                        .observe(viewLifecycleOwner) {
                            if (it) {
                                pd.dismiss()
                                // mark exit as true so we can exit the onBackPressed callback
                                isExit = true
                                requireActivity().onBackPressed()
                            } else {
                                Snackbar.make(
                                    binding.root,
                                    "Oops! Something went wrong!",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        }
                } else {
                    manageScholarshipViewModel.addScholarship(scholarship)
                        .observe(viewLifecycleOwner) {
                            if (it) {
                                pd.dismiss()
                                isExit = true
                                requireActivity().onBackPressed()
                            } else {
                                Snackbar.make(
                                    binding.root,
                                    "Oops! Something went wrong!",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        }
                }
            } else {
                Snackbar.make(
                    binding.root,
                    "Ensure all fields are valid!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
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