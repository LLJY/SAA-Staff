package com.saa.staff.activities.mainui.ManageFellowship

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.saa.staff.R
import com.saa.staff.databinding.ManageFellowshipFragmentBinding
import javax.inject.Inject

class ManageFellowshipFragment : Fragment() {
    val viewModel: ManageFellowshipViewModel by viewModels()
    lateinit var binding: ManageFellowshipFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ManageFellowshipFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}