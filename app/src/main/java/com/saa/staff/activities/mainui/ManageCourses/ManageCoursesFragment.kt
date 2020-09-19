package com.saa.staff.activities.mainui.ManageCourses

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.saa.staff.R
import com.saa.staff.adapters.CoursesRecyclerAdapter
import com.saa.staff.databinding.ManageCoursesFragmentBinding
import com.saa.staff.interfaces.Disposable
import com.saa.staff.models.Course
import javax.inject.Inject

class ManageCoursesFragment : Fragment() {
    val viewModel: ManageCoursesViewModel by viewModels()
    private lateinit var binding: ManageCoursesFragmentBinding
    private lateinit var adapter: CoursesRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ManageCoursesFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = CoursesRecyclerAdapter(
            requireContext(),

        )
        // dummy data
        val items = listOf(
            Course(
                "adana",
                "An Interesting Course",
                System.currentTimeMillis(),
                System.currentTimeMillis()+(22*24*60*10000),
                1000f,
                "a",
                "a",
                "a",
                "English",
                "a",
                System.currentTimeMillis()
            )
        )
        // setup the recyclerview
        binding.coursesRecycler.adapter = adapter
        binding.coursesRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(items)
        binding.swipeRefreshLayout.setOnRefreshListener {
            //TODO get new list and submit it
            binding.swipeRefreshLayout.isRefreshing = false
        }
        adapter.deleteClick.subscribe {
            Snackbar.make(binding.coordinator, "Delete clicked", Snackbar.LENGTH_SHORT).show()
        }
        adapter.detailsButtonClick.subscribe{
            Snackbar.make(binding.coordinator, "Details clicked", Snackbar.LENGTH_SHORT).show()
        }
        adapter.editInfoClick.subscribe {
            Snackbar.make(binding.coordinator, "Edit clicked", Snackbar.LENGTH_SHORT).show()
        }
    }

}