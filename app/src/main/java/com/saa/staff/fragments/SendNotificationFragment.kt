package com.saa.staff.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.saa.staff.databinding.SendNotificationFragmentBinding
import com.saa.staff.viewmodels.SendNotificationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendNotificationFragment : Fragment() {
    val viewModel: SendNotificationViewModel by viewModels()
    lateinit var binding: SendNotificationFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SendNotificationFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sendButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Are you sure?")
            builder.setMessage("Do you want to send this notification?")
            // ensure that fields are not blank, so we don't send an empty notification
            if (binding.titleText.editText!!.text.isNotBlank() && binding.message.editText!!.text.isNotBlank()) {
                builder.setNegativeButton("NO") { dialog, which ->

                }
                builder.setPositiveButton("YES") { dialog, which ->
                    viewModel.sendNotification(
                        binding.titleText.editText!!.text.toString(),
                        binding.message.editText!!.text.toString()
                    ).observe(viewLifecycleOwner) {
                        if (it) {
                            Snackbar.make(
                                binding.root,
                                "Notification Sent!",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        } else {
                            Snackbar.make(
                                binding.root,
                                "Oops! Something went wrong!",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                }
                builder.show()
            } else {
                Snackbar.make(binding.root, "Ensure all fields are valid!", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

}