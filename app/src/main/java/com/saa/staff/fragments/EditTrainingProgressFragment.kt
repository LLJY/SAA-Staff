package com.saa.staff.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.saa.staff.adapters.EditTrainingUsersRecyclerAdapter
import com.saa.staff.databinding.EditTrainingProgressFragmentBinding
import com.saa.staff.viewmodels.EditTrainingProgressViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditTrainingProgressFragment : Fragment() {
    private val viewModel: EditTrainingProgressViewModel by activityViewModels()
    @Inject
    lateinit var pd: ProgressDialog
    private lateinit var binding: EditTrainingProgressFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditTrainingProgressFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = EditTrainingUsersRecyclerAdapter(requireContext())
        // show loading dialog until loaded
        pd.show()
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getApplicants().observe(viewLifecycleOwner) {
            adapter.submitList(it)
            pd.dismiss()
        }
        adapter.changeSpinnerSubject.subscribe { item ->
            pd.show()
            viewModel.updateApplication(item).observe(viewLifecycleOwner) {
                pd.dismiss()
                if (it) {
                    Snackbar.make(
                        binding.root,
                        "User training status updated!",
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    Snackbar.make(binding.root, "Oops! Something went wrong!", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

}