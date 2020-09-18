package com.saa.staff.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saa.staff.R
import com.saa.staff.viewModels.TrainingProgressViewModel

class TrainingProgressFragment : Fragment() {

    companion object {
        fun newInstance() = TrainingProgressFragment()
    }

    private lateinit var viewModel: TrainingProgressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.training_progress_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TrainingProgressViewModel::class.java)
        // TODO: Use the ViewModel
    }

}