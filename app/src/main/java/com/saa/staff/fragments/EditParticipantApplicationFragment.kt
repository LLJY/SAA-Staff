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
import com.saa.staff.adapters.ApproveRejectUserRecyclerAdapter
import com.saa.staff.databinding.EditParticipantApplicationFragmentBinding
import com.saa.staff.viewmodels.EditParticipantApplicationViewModel
import com.saa.staff.viewmodels.ViewUserInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditParticipantApplicationFragment : Fragment() {
    private val viewModel: EditParticipantApplicationViewModel by activityViewModels()
    private val viewUserInfoViewModel: ViewUserInfoViewModel by activityViewModels()
    private lateinit var binding: EditParticipantApplicationFragmentBinding
    @Inject
    lateinit var pd: ProgressDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            EditParticipantApplicationFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ApproveRejectUserRecyclerAdapter(requireContext())
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        pd.show()
        viewModel.getApplicants(viewModel.courseUUID).observe(viewLifecycleOwner) {
            pd.dismiss()
            adapter.submitList(it)
        }
        adapter.approveButtonClickSubject.subscribe { item ->
            pd.show()
            viewModel.updateApplication(item).observe(viewLifecycleOwner) {
                pd.dismiss()
                if (it) {
                    Snackbar.make(
                        binding.root,
                        "User training status updated!",
                        Snackbar.LENGTH_LONG
                    ).show()
                    val itemIndex = adapter.currentList.indexOf(item)
                    // set the progress type to rejected and update the list with it
                    item.progressType = 2
                    val list = adapter.currentList.toMutableList()
                    list.set(itemIndex, item)
                    adapter.submitList(list)
                    adapter.notifyDataSetChanged()
                } else {
                    Snackbar.make(binding.root, "Oops! Something went wrong!", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        }
        binding.searchText.editText!!.addTextChangedListener { text ->
            if (text.toString().isNotBlank()) {
                adapter.submitList(viewModel.userApplications?.filter {
                    it.toString().toLowerCase().contains(text.toString().toLowerCase())
                })
            } else {
                adapter.submitList(viewModel.userApplications)
            }
        }
        adapter.viewInfoClickSubject.subscribe {
            viewUserInfoViewModel.user = it.user
            findNavController().navigate(EditParticipantApplicationFragmentDirections.actionEditParticipantApplicationFragmentToViewUserInfoFragment())
        }

        adapter.rejectButtonClickSubject.subscribe { item ->
            pd.show()
            viewModel.updateApplication(item).observe(viewLifecycleOwner) {
                pd.dismiss()
                if (it) {
                    Snackbar.make(
                        binding.root,
                        "User training status updated!",
                        Snackbar.LENGTH_LONG
                    ).show()
                    val itemIndex = adapter.currentList.indexOf(item)
                    // set the progress type to rejected and update the list with it
                    var itemModified = item
                    itemModified.progressType = 0
                    val list = adapter.currentList.toMutableList()
                    list[itemIndex] = itemModified
                    println(item.toString())
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