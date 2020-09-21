package com.saa.staff.activities.mainui.ManageDiploma

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.adapters.DiplomasRecyclerAdapter
import com.saa.staff.databinding.ManageDiplomaFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageDiplomaFragment : Fragment() {
    val viewModel: ManageDiplomaViewModel by viewModels()
    val addEditDiplomaViewModel: AddEditDiplomaViewModel by activityViewModels()
    val viewDiplomasViewModel: ViewDiplomaViewModel by activityViewModels()
    lateinit var binding: ManageDiplomaFragmentBinding
    lateinit var adapter: DiplomasRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ManageDiplomaFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = DiplomasRecyclerAdapter(requireContext())
        if (viewModel.diplomas == null) {
            refreshRv()
        } else {
            adapter.submitList(viewModel.diplomas)
        }
        binding.diplomasRecycler.adapter = adapter
        binding.diplomasRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.diplomasRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    binding.fab.hide()
                } else if (dy < 0) {
                    binding.fab.show()
                }
            }
        })
        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshRv()
        }
        adapter.deleteClick.subscribe {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Are you sure?")
            builder.setMessage("Do you want to delete \"${it.title}\" ?")
            builder.setNegativeButton("NO") { dialog, which ->

            }
            builder.setPositiveButton("YES") { dialog, which ->
                viewModel.deleteDiploma(it).observe(viewLifecycleOwner, {
                    refreshRv()
                })
            }
            builder.show()
        }
        adapter.detailsButtonClick.subscribe {
            viewDiplomasViewModel.diploma = it
            findNavController().navigate(ManageDiplomaFragmentDirections.actionManageDiplomaFragmentToViewDiplomaFragment())
        }
        adapter.editInfoClick.subscribe {
            addEditDiplomaViewModel.clearViewModel()
            addEditDiplomaViewModel.isEdit = true
            addEditDiplomaViewModel.diploma = it
            findNavController().navigate(ManageDiplomaFragmentDirections.actionManageDiplomaFragmentToAddEditDiplomaFragment())
        }

    }

    /**
     * refresh recyclerview
     */
    fun refreshRv() {
        viewModel.getDiplomas().observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.diplomas = it
            adapter.submitList(it)
        }
    }

}