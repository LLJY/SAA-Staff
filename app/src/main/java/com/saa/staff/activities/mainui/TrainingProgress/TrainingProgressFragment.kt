package com.saa.staff.activities.mainui.TrainingProgress

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.saa.staff.R
import com.saa.staff.databinding.TrainingProgressFragmentBinding
import javax.inject.Inject

class TrainingProgressFragment : Fragment() {
    val viewModel: TrainingProgressViewModel by viewModels()
    lateinit var binding: TrainingProgressFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TrainingProgressFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}