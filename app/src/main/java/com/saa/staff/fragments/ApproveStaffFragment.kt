package com.saa.staff.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.saa.staff.adapters.ApproveRejectEmployeeRecyclerAdapter
import com.saa.staff.databinding.ApproveStaffFragmentBinding
import com.saa.staff.viewmodels.ApproveStaffViewModel
import com.saa.staff.viewmodels.ViewStaffViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ApproveStaffFragment : Fragment() {
    val viewModel: ApproveStaffViewModel by activityViewModels()
    val viewStaffViewModel: ViewStaffViewModel by activityViewModels()

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
        adapter.viewInfoClickSubject.subscribe {

        }
        binding.searchText.editText!!.addTextChangedListener {
            if (it.toString().isNotBlank()) {
                adapter.submitList(viewModel.employees?.filter {
                    it.fullName.toLowerCase().contains(it.toString().toLowerCase())
                })
            } else {
                adapter.submitList(viewModel.employees)
            }
        }
        adapter.approveButtonClickSubject.subscribe { item ->
            pd.show()
            val newItem = item
            newItem.approvalStatus = 2
            viewModel.updateEmployeeStatus(newItem).observe(viewLifecycleOwner) {
                pd.dismiss()
                if (it) {
                    Snackbar.make(
                        binding.root,
                        "User training status updated!",
                        Snackbar.LENGTH_LONG
                    ).show()
                    val itemIndex = adapter.currentList.indexOf(item)
                    val list = adapter.currentList.toMutableList()
                    list[itemIndex] = newItem
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
            val newItem = item
            newItem.approvalStatus = 0
            viewModel.updateEmployeeStatus(newItem).observe(viewLifecycleOwner) {
                pd.dismiss()
                if (it) {
                    Snackbar.make(
                        binding.root,
                        "User training status updated!",
                        Snackbar.LENGTH_LONG
                    ).show()
                    val itemIndex = adapter.currentList.indexOf(item)
                    val list = adapter.currentList.toMutableList()
                    list[itemIndex] = newItem
                    adapter.submitList(list)
                    adapter.notifyDataSetChanged()
                } else {
                    Snackbar.make(binding.root, "Oops! Something went wrong!", Snackbar.LENGTH_LONG)
                        .show()
                }
            }

        }
        adapter.viewInfoClickSubject.subscribe { item ->
            viewStaffViewModel.employee = item
            findNavController().navigate(ApproveStaffFragmentDirections.actionApproveStaffFragmentToViewStaffFragment())
        }
    }

}