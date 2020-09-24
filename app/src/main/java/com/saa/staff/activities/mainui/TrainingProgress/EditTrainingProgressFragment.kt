package com.saa.staff.activities.mainui.TrainingProgress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.saa.staff.databinding.EditTrainingProgressFragmentBinding

class EditTrainingProgressFragment : Fragment() {
    private val viewModel: EditTrainingProgressViewModel by activityViewModels()
    private lateinit var binding: EditTrainingProgressFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditTrainingProgressFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}