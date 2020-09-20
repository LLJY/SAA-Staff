package com.saa.staff.activities.mainui.ManageCourses

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.saa.staff.adapters.CoursesRecyclerAdapter
import com.saa.staff.databinding.ManageCoursesFragmentBinding
import com.saa.staff.models.Course
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageCoursesFragment : Fragment() {
    val viewModel: ManageCoursesViewModel by activityViewModels()
    val addEditCourseViewModel: AddEditCourseViewModel by activityViewModels()
    val viewCourseViewModel: ViewCourseInfoViewModel by activityViewModels()
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

        viewModel.getCourses().observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
        // setup the recyclerview
        binding.coursesRecycler.adapter = adapter
        binding.coursesRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshRv()
        }
        adapter.deleteClick.subscribe {
            // TODO send a delete request
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Are you sure you want to delete ${it.title} ?")
            builder.setTitle("Confirm")
            builder.setNegativeButton("NO"){dialog, which ->

            }
            builder.setPositiveButton("YES") { dialog, which ->
                viewModel.deleteCourse(it).observe(viewLifecycleOwner, {
                    refreshRv()
                })
            }
            builder.show()
        }
        // subscribe for click information and launch the views accordingly
        adapter.detailsButtonClick.subscribe{
            viewCourseViewModel.clearViewModel()
            viewCourseViewModel.course = it
            findNavController().navigate(ManageCoursesFragmentDirections.actionManageCoursesFragmentToViewCourseInfoFragment())
        }
        adapter.editInfoClick.subscribe {
            addEditCourseViewModel.isEdit = true
            // ensure that viewmodel is cleared
            addEditCourseViewModel.clearViewModel()
            addEditCourseViewModel.course = it
            findNavController().navigate(ManageCoursesFragmentDirections.actionManageCoursesFragmentToAddEditCourseFragment())
        }
        binding.fab.setOnClickListener {
            addEditCourseViewModel.isEdit = false
            // ensure that viewmodel is cleared
            addEditCourseViewModel.clearViewModel()
            findNavController().navigate(ManageCoursesFragmentDirections.actionManageCoursesFragmentToAddEditCourseFragment())
        }
    }
    fun refreshRv(){
        viewModel.getCourses().observe(viewLifecycleOwner, {
            binding.swipeRefreshLayout.isRefreshing = false
            adapter.submitList(it)
        })
    }

}