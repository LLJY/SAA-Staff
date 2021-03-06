package com.saa.staff.fragments

import android.app.AlertDialog
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
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.adapters.ScholarshipRecyclerAdapter
import com.saa.staff.databinding.ManageScholarshipFragmentBinding
import com.saa.staff.models.Scholarship
import com.saa.staff.viewmodels.AddEditScholarshipViewModel
import com.saa.staff.viewmodels.ManageScholarshipViewModel
import com.saa.staff.viewmodels.ViewScholarshipViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ManageScholarshipFragment : Fragment() {
    val viewModel: ManageScholarshipViewModel by activityViewModels()
    val viewScholarshipViewModel: ViewScholarshipViewModel by activityViewModels()
    val addEditScholarshipViewModel: AddEditScholarshipViewModel by activityViewModels()
    lateinit var binding: ManageScholarshipFragmentBinding
    private lateinit var adapter: ScholarshipRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ManageScholarshipFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = ScholarshipRecyclerAdapter(requireContext())
        binding.scholarshipRecycler.adapter = adapter
        binding.scholarshipRecycler.layoutManager = LinearLayoutManager(requireContext())
        // refresh recyclerview to get data
        refreshRv()
        binding.scholarshipRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            // hide fab on scroll for candy
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
            refreshRv(true)
        }
        binding.searchText.editText!!.addTextChangedListener {
            adapter.submitList(search(it.toString()))
        }
        // use main coroutine scope to avoid spawning unnecessary threads and savc resources
        lifecycleScope.launch(Dispatchers.Main) {
            adapter.detailsButtonClick.collect {
                viewScholarshipViewModel.scholarship = it
                findNavController().navigate(ManageScholarshipFragmentDirections.actionManageScholarshipFragmentToViewScholarshipFragment())
            }
        }
        lifecycleScope.launch(Dispatchers.Main) {
            adapter.editInfoClick.collect {
                addEditScholarshipViewModel.resetViewModel()
                addEditScholarshipViewModel.scholarship = it
                addEditScholarshipViewModel.isEdit = true
                findNavController().navigate(ManageScholarshipFragmentDirections.actionManageScholarshipFragmentToAddEditScholarshipFragment())
            }
        }
        lifecycleScope.launch(Dispatchers.Main) {
            adapter.deleteClick.collect {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Are you sure?")
                builder.setMessage("Do you want to delete \"${it.title}\" ?")
                builder.setNegativeButton("NO") { dialog, which ->

                }
                builder.setPositiveButton("YES") { dialog, which ->
                    viewModel.deleteScholarship(it).observe(viewLifecycleOwner, {
                        refreshRv(true)
                    })
                }
                builder.show()
            }
        }
        binding.fab.setOnClickListener {
            addEditScholarshipViewModel.resetViewModel()
            findNavController().navigate(ManageScholarshipFragmentDirections.actionManageScholarshipFragmentToAddEditScholarshipFragment())
        }
    }

    /**
     * refresh recyclerview
     */
    fun refreshRv(refresh: Boolean = false) {
        viewModel.getScholarships(refresh).observe(viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.scholarships = it
            adapter.submitList(it)
        }
    }

    fun search(query: String): List<Scholarship>? {
        // by the time there is an opportunity to execute this, these will not be null.
        if (query.isNotBlank()) {
            return viewModel.scholarships?.filter {
                it.title.toLowerCase().contains(query.toLowerCase())
            }?.distinct()
        } else {
            return viewModel.scholarships
        }
    }


}