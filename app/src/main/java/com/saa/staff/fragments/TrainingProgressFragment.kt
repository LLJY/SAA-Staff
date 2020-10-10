package com.saa.staff.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.saa.staff.R
import com.saa.staff.adapters.TrainingCoursesRecyclerAdapter
import com.saa.staff.adapters.TrainingDiplomasRecyclerAdapter
import com.saa.staff.adapters.TrainingFellowshipsRecyclerAdapter
import com.saa.staff.adapters.TrainingScholarshipsRecyclerAdapter
import com.saa.staff.databinding.TrainingProgressFragmentBinding
import com.saa.staff.models.Course
import com.saa.staff.models.Diploma
import com.saa.staff.models.Fellowship
import com.saa.staff.models.Scholarship
import com.saa.staff.viewmodels.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingProgressFragment : Fragment() {
    val viewModel: TrainingProgressViewModel by viewModels()
    val manageCoursesViewModel: ManageCoursesViewModel by activityViewModels()
    val manageDiplomaViewModel: ManageDiplomaViewModel by activityViewModels()
    val manageScholarshipViewModel: ManageScholarshipViewModel by activityViewModels()
    val manageFellowshipViewModel: ManageFellowshipViewModel by activityViewModels()
    val editTrainingProgressViewModel: EditTrainingProgressViewModel by activityViewModels()

    // add a dummy text watcher for null safety
    private var searchTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
        }

    }
    lateinit var binding: TrainingProgressFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TrainingProgressFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val courseTypes = listOf("Courses", "Fellowships", "Scholarships", "Diplomas")
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            courseTypes
        )
        (binding.typeSpinner.editText!! as AutoCompleteTextView).setAdapter(arrayAdapter)
        (binding.typeSpinner.editText!! as AutoCompleteTextView).setOnItemClickListener { parent, view, position, id ->
            setAdapter(position)
        }
        // set the default selection as courses
        (binding.typeSpinner.editText!! as AutoCompleteTextView).setText("Courses", false)
        setAdapter(0)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    fun search(courseIndex: Int, query: String): List<Any>? {
        // by the time there is an opportunity to execute this, these will not be null.
        if (query.isNotBlank()) {
            return when (courseIndex) {
                0 -> manageCoursesViewModel.courses?.filter {
                    it.title.toLowerCase().contains(query.toLowerCase())
                }
                1 -> manageFellowshipViewModel.fellowships?.filter {
                    it.title.toLowerCase().contains(query.toLowerCase())
                }
                2 -> manageScholarshipViewModel.scholarships?.filter {
                    it.title.toLowerCase().contains(query.toLowerCase())
                }
                3 -> manageDiplomaViewModel.diplomas?.filter {
                    it.title.toLowerCase().contains(query.toLowerCase())
                }
                else -> null
            }
        } else {
            return when (courseIndex) {
                0 -> manageCoursesViewModel.courses
                1 -> manageFellowshipViewModel.fellowships
                2 -> manageScholarshipViewModel.scholarships
                3 -> manageDiplomaViewModel.diplomas
                else -> null
            }
        }
    }

    /**
     * sets the adapter according to the selected index of the spinner
     */
    fun setAdapter(position: Int) {
        if (searchTextWatcher != null) {
            binding.searchText.editText!!.removeTextChangedListener(searchTextWatcher)
        }
        when (position) {
            0 -> {
                val adapter = TrainingCoursesRecyclerAdapter(requireContext())
                binding.recycler.adapter = adapter
                manageCoursesViewModel.getCourses().observe(viewLifecycleOwner) {
                    adapter.submitList(it)
                }
                adapter.onClickSubject.subscribe {
                    editTrainingProgressViewModel.courseTypeIndex = 0
                    editTrainingProgressViewModel.courseUUID = it.uuid
                    findNavController().navigate(TrainingProgressFragmentDirections.actionTrainingProgressFragmentToEditTrainingProgressFragment())
                }

                searchTextWatcher = object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        adapter.submitList(search(0, s.toString()) as MutableList<Course>?)
                    }

                }
            }
            1 -> {
                val adapter = TrainingFellowshipsRecyclerAdapter(requireContext())
                binding.recycler.adapter = adapter
                manageFellowshipViewModel.getFellowships().observe(viewLifecycleOwner) {
                    adapter.submitList(it)
                }
                adapter.onClickSubject.subscribe {
                    editTrainingProgressViewModel.courseTypeIndex = 1
                    editTrainingProgressViewModel.courseUUID = it.uuid
                    findNavController().navigate(TrainingProgressFragmentDirections.actionTrainingProgressFragmentToEditTrainingProgressFragment())
                }
                searchTextWatcher = object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        adapter.submitList(search(1, s.toString()) as MutableList<Fellowship>?)
                    }

                }
            }
            2 -> {
                val adapter = TrainingScholarshipsRecyclerAdapter(requireContext())
                binding.recycler.adapter = adapter
                manageScholarshipViewModel.getScholarships().observe(viewLifecycleOwner) {
                    adapter.submitList(it)
                }
                adapter.onClickSubject.subscribe {
                    editTrainingProgressViewModel.courseTypeIndex = 2
                    editTrainingProgressViewModel.courseUUID = it.uuid
                    findNavController().navigate(TrainingProgressFragmentDirections.actionTrainingProgressFragmentToEditTrainingProgressFragment())
                }
                searchTextWatcher = object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        adapter.submitList(search(2, s.toString()) as MutableList<Scholarship>?)
                    }

                }

            }
            3 -> {
                val adapter = TrainingDiplomasRecyclerAdapter(requireContext())
                binding.recycler.adapter = adapter
                manageDiplomaViewModel.getDiplomas().observe(viewLifecycleOwner) {
                    adapter.submitList(it)
                }
                adapter.onClickSubject.subscribe {
                    editTrainingProgressViewModel.courseTypeIndex = 3
                    editTrainingProgressViewModel.courseUUID = it.uuid
                    findNavController().navigate(TrainingProgressFragmentDirections.actionTrainingProgressFragmentToEditTrainingProgressFragment())
                }
                searchTextWatcher = object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }

                    override fun afterTextChanged(s: Editable?) {
                        adapter.submitList(search(3, s.toString()) as MutableList<Diploma>?)
                    }

                }
            }
        }
        binding.searchText.editText!!.addTextChangedListener(searchTextWatcher)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }

}