package com.saa.staff.activities.mainui.ReviewApplication

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.saa.staff.R
import com.saa.staff.databinding.ReviewApplicationFragmentBinding
import javax.inject.Inject

class ReviewApplicationFragment : Fragment() {
    val viewModel: ReviewApplicationViewModel by viewModels()
    lateinit var binding: ReviewApplicationFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ReviewApplicationFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}