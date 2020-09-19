package com.saa.staff.activities.mainui.ManageDiploma

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.saa.staff.R
import com.saa.staff.databinding.ManageDiplomaFragmentBinding
import javax.inject.Inject

class ManageDiplomaFragment : Fragment() {
    val viewModel: ManageDiplomaViewModel by viewModels()
    lateinit var binding: ManageDiplomaFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ManageDiplomaFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}