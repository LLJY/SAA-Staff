package com.saa.staff.fragments

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.saa.staff.adapters.FellowshipsRecyclerAdapter
import com.saa.staff.databinding.ManageFellowshipFragmentBinding
import com.saa.staff.models.Fellowship
import com.saa.staff.viewmodels.AddEditFellowshipViewModel
import com.saa.staff.viewmodels.ManageFellowshipViewModel
import com.saa.staff.viewmodels.ViewFellowshipViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ManageFellowshipFragment : Fragment() {
    val viewModel: ManageFellowshipViewModel by activityViewModels()
    val viewFellowshipViewModel: ViewFellowshipViewModel by activityViewModels()
    val addEditFellowshipViewModel: AddEditFellowshipViewModel by activityViewModels()
    lateinit var binding: ManageFellowshipFragmentBinding
    lateinit var adapter: FellowshipsRecyclerAdapter
    @Inject lateinit var pd: ProgressDialog
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
            refreshRv(true)
        }
        binding.searchText.editText!!.addTextChangedListener {
            adapter.submitList(search(it.toString()))
        }
        // setup the recyclerview onclicklisteners
        // use main coroutine scope to avoid spawning unnecessary threads and savc resources
        lifecycleScope.launch(Dispatchers.Main) {
            adapter.detailsButtonClick.collect {
                viewFellowshipViewModel.fellowShip = it
                findNavController().navigate(ManageFellowshipFragmentDirections.actionManageFellowshipFragmentToViewFellowshipFragment())
            }
        }
        binding.fabFellowship.setOnClickListener {
            addEditFellowshipViewModel.clearViewModel()
            findNavController().navigate(ManageFellowshipFragmentDirections.actionManageFellowshipFragmentToAddEditFellowshipFragment())
        }
        lifecycleScope.launch(Dispatchers.Main) {
            adapter.editInfoClick.collect {
                addEditFellowshipViewModel.clearViewModel()
                addEditFellowshipViewModel.isEdit = true
                addEditFellowshipViewModel.fellowShip = it
                findNavController().navigate(ManageFellowshipFragmentDirections.actionManageFellowshipFragmentToAddEditFellowshipFragment())
            }
        }
        lifecycleScope.launch(Dispatchers.Main) {
            adapter.deleteClick.collect {
                val dialog = AlertDialog.Builder(requireContext())
                dialog.setTitle("Are you sure?")
                dialog.setMessage("Do you want to delete \"${it.title}\" ?")
                dialog.setPositiveButton("YES") { dialogInterface: DialogInterface, i: Int ->
                    pd.show()
                    viewModel.deleteFellowship(it).observe(viewLifecycleOwner) {
                        pd.dismiss()
                        if (it) {
                            refreshRv(true)
                        } else {
                            Snackbar.make(
                                binding.root,
                                "Oops! Something went wrong!",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                }
                dialog.setNegativeButton("NO") { dialogInterface: DialogInterface, i: Int ->

                }
                dialog.show()
            }
        }
    }

    /**
     * Refresh the recyclerview by getting fellowships from the backend and asynchronously updating the
     * List using submitList, which calculates the diff in another thread.
     */
    fun refreshRv(refresh: Boolean = false) {
        viewModel.getFellowships(refresh).observe(viewLifecycleOwner, {
            // set the refresh to not refreshing
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.fellowships = it
            adapter.submitList(it)
        })
    }

    fun search(query: String): List<Fellowship>? {
        // by the time there is an opportunity to execute this, these will not be null.
        if (query.isNotBlank()) {
            return viewModel.fellowships?.filter {
                it.title.toLowerCase().contains(query.toLowerCase())
            }
        } else {
            return viewModel.fellowships
        }
    }

}