package com.saa.staff.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.adapters.DiplomasRecyclerAdapter
import com.saa.staff.databinding.ManageDiplomaFragmentBinding
import com.saa.staff.models.Diploma
import com.saa.staff.viewmodels.AddEditDiplomaViewModel
import com.saa.staff.viewmodels.ManageDiplomaViewModel
import com.saa.staff.viewmodels.ViewDiplomaViewModel
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
        refreshRv()
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
        binding.searchText.editText!!.addTextChangedListener {
            adapter.submitList(search(it.toString()))
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshRv(true)
        }
        adapter.deleteClick.subscribe {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Are you sure?")
            builder.setMessage("Do you want to delete \"${it.title}\" ?")
            builder.setNegativeButton("NO") { dialog, which ->

            }
            builder.setPositiveButton("YES") { dialog, which ->
                viewModel.deleteDiploma(it).observe(viewLifecycleOwner, {
                    refreshRv(true)
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
        binding.fab.setOnClickListener {
            addEditDiplomaViewModel.clearViewModel()
            findNavController().navigate(ManageDiplomaFragmentDirections.actionManageDiplomaFragmentToAddEditDiplomaFragment())
        }
    }

    /**
     * refresh recyclerview
     */
    fun refreshRv(refresh: Boolean = false) {
        viewModel.getDiplomas(refresh).observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.diplomas = it
            adapter.submitList(it)
        }
    }

    fun search(query: String): List<Diploma>? {
        // by the time there is an opportunity to execute this, these will not be null.
        if (query.isNotBlank()) {
            return viewModel.diplomas?.filter {
                it.title.toLowerCase().contains(query.toLowerCase())
            }
        } else {
            return viewModel.diplomas
        }
    }

}