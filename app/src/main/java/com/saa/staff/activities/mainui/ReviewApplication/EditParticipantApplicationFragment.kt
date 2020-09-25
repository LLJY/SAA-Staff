package com.saa.staff.activities.mainui.ReviewApplication

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
import com.saa.staff.adapters.ApproveRejectUserRecyclerAdapter
import com.saa.staff.databinding.EditParticipantApplicationFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditParticipantApplicationFragment : Fragment() {
    private val viewModel: EditParticipantApplicationViewModel by activityViewModels()
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
        viewModel.getApplicants().observe(viewLifecycleOwner) {
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