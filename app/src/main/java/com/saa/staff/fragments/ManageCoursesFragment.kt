package com.saa.staff.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saa.staff.activities.mainui.ManageCourses.ManageCoursesFragmentDirections
import com.saa.staff.adapters.CoursesRecyclerAdapter
import com.saa.staff.databinding.ManageCoursesFragmentBinding
import com.saa.staff.viewModels.AddEditCourseViewModel
import com.saa.staff.viewModels.ManageCoursesViewModel
import com.saa.staff.viewModels.ViewCourseInfoViewModel
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

        refreshRv()
        // setup the recyclerview
        binding.coursesRecycler.adapter = adapter
        binding.coursesRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshRv(true)
        }
        adapter.deleteClick.subscribe {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Are you sure?")
            builder.setMessage("Do you want to delete \"${it.title}\" ?")
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
        binding.coursesRecycler.addOnScrollListener(object: RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy > 0){
                    binding.fab.hide()
                }else if(dy<0){
                    binding.fab.show()
                }
            }
        })
    }
    fun refreshRv(refresh: Boolean = false){
        binding.swipeRefreshLayout.isRefreshing = true
        viewModel.getCourses(refresh).observe(viewLifecycleOwner, {
            binding.swipeRefreshLayout.isRefreshing = false
            adapter.submitList(it)
        })
    }

}