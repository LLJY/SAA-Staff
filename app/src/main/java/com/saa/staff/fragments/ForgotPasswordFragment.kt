package com.saa.staff.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.saa.staff.databinding.ForgotPasswordFragmentBinding
import com.saa.staff.viewmodels.ForgotPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {
    private val viewModel: ForgotPasswordViewModel by viewModels()
    private lateinit var binding: ForgotPasswordFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ForgotPasswordFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.emailText.editText!!.addTextChangedListener {
            viewModel.email = it.toString()
            if (it.isNullOrBlank()) {
                binding.emailText.error = "Required"
            } else {
                binding.emailText.error = null
            }
        }
        binding.passwordText.editText!!.addTextChangedListener {
            viewModel.password = it.toString()
            if (it.isNullOrBlank()) {
                binding.passwordText.error = "Required"
            } else {
                binding.passwordText.error = null
            }
        }
        binding.doneButton.setOnClickListener {
            if (!binding.emailText.editText!!.text.isNullOrEmpty() && !binding.passwordText.editText!!.text.isNullOrEmpty()) {
                viewModel.resetPassword().observe(viewLifecycleOwner) {
                    if (it) {
                        Toast.makeText(
                            context,
                            "Password Successfully Changed!",
                            Toast.LENGTH_SHORT
                        ).show()
                        activity?.onBackPressed()
                    } else {
                        Snackbar.make(
                            binding.root,
                            "Oops! Something went wrong!",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                Snackbar.make(binding.root, "Ensure all fields are valid!", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

}