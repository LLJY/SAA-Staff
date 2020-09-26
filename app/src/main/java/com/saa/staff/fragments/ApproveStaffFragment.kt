package com.saa.staff.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.saa.staff.adapters.ApproveRejectEmployeeRecyclerAdapter
import com.saa.staff.databinding.ApproveStaffFragmentBinding
import com.saa.staff.viewmodels.ApproveStaffViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ApproveStaffFragment : Fragment() {
    val viewModel: ApproveStaffViewModel by activityViewModels()

    @Inject
    lateinit var pd: ProgressDialog
    lateinit var binding: ApproveStaffFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ApproveStaffFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ApproveRejectEmployeeRecyclerAdapter(requireContext())
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        // show loading dialog while getting employees asynchronously
        pd.show()
        viewModel.getEmployees().observe(viewLifecycleOwner) {
            pd.dismiss()
            adapter.submitList(it)
        }
        adapter.approveButtonClickSubject.subscribe { item ->
            pd.show()
            viewModel.updateEmployeeStatus(item).observe(viewLifecycleOwner) {
                pd.dismiss()
                if (it) {
                    Snackbar.make(
                        binding.root,
                        "User training status updated!",
                        Snackbar.LENGTH_LONG
                    ).show()
                    val itemIndex = adapter.currentList.indexOf(item)
                    // set the approval status to accepted and update the list with it
                    item.approvalStatus = 2
                    val list = adapter.currentList.toMutableList()
                    list[itemIndex] = item
                    adapter.submitList(list)
                    adapter.notifyDataSetChanged()
                } else {
                    Snackbar.make(binding.root, "Oops! Something went wrong!", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
        adapter.rejectButtonClickSubject.subscribe { item ->
            pd.show()
            viewModel.updateEmployeeStatus(item).observe(viewLifecycleOwner) {
                pd.dismiss()
                if (it) {
                    Snackbar.make(
                        binding.root,
                        "User training status updated!",
                        Snackbar.LENGTH_LONG
                    ).show()
                    val itemIndex = adapter.currentList.indexOf(item)
                    // set the approval status to rejected and update the list with it
                    item.approvalStatus = 0
                    val list = adapter.currentList.toMutableList()
                    list[itemIndex] = item
                    adapter.submitList(list)
                    adapter.notifyDataSetChanged()
                } else {
                    Snackbar.make(binding.root, "Oops! Something went wrong!", Snackbar.LENGTH_LONG)
                        .show()
                }
            }

        }
    }

}