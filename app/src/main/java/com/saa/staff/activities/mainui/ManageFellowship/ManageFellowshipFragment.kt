package com.saa.staff.activities.mainui.ManageFellowship

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.saa.staff.R
import com.saa.staff.adapters.FellowshipsRecyclerAdapter
import com.saa.staff.databinding.ManageFellowshipFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ManageFellowshipFragment : Fragment() {
    val viewModel: ManageFellowshipViewModel by activityViewModels()
    val viewFellowshipViewModel: ViewFellowshipViewModel by activityViewModels()
    val addEditFellowshipViewModel: AddEditFellowshipViewModel by activityViewModels()
    lateinit var binding: ManageFellowshipFragmentBinding
    lateinit var adapter: FellowshipsRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ManageFellowshipFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // setup recyclerview
        adapter = FellowshipsRecyclerAdapter(requireContext())
        binding.fellowshipRecycler.adapter = adapter
        binding.fellowshipRecycler.layoutManager = LinearLayoutManager(requireContext())
        refreshRv()
        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshRv()
        }
        // setup the recyclerview onclicklisteners
        adapter.detailsButtonClick.subscribe {
            viewFellowshipViewModel.fellowShip = it
            findNavController().navigate(ManageFellowshipFragmentDirections.actionManageFellowshipFragmentToViewFellowshipFragment())
        }
        adapter.editInfoClick.subscribe{
            addEditFellowshipViewModel.clearViewModel()
            addEditFellowshipViewModel.isEdit=true
            addEditFellowshipViewModel.fellowShip = it
            findNavController().navigate(ManageFellowshipFragmentDirections.actionManageFellowshipFragmentToAddEditFellowshipFragment())
        }
        binding.fabFellowship.setOnClickListener {
            addEditFellowshipViewModel.clearViewModel()
            findNavController().navigate(ManageFellowshipFragmentDirections.actionManageFellowshipFragmentToAddEditFellowshipFragment())
        }
        adapter.deleteClick.subscribe{
            val dialog = AlertDialog.Builder(requireContext())
            dialog.setTitle("Are you sure?")
            dialog.setMessage("Do you want to delete \"${it.title}\" ?")
            dialog.setPositiveButton("YES", { dialogInterface: DialogInterface, i: Int ->
                Snackbar.make(binding.root, "Delete is a todo feature", Snackbar.LENGTH_SHORT).show()
            })
            dialog.setNegativeButton("NO", { dialogInterface: DialogInterface, i: Int ->

            })
            dialog.show()
        }

    }

    /**
     * Refresh the recyclerview by getting fellowships from the backend and asynchronously updating the
     * List using submitList, which calculates the diff in another thread.
     */
    fun refreshRv(){
        viewModel.getFellowships().observe(viewLifecycleOwner, {
            // set the refresh to not refreshing
            binding.swipeRefreshLayout.isRefreshing = false
            adapter.submitList(it)
        })
    }

}