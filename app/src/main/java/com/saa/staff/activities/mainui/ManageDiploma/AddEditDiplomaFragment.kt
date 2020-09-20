package com.saa.staff.activities.mainui.ManageDiploma

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saa.staff.R

class AddEditDiplomaFragment : Fragment() {

    companion object {
        fun newInstance() = AddEditDiplomaFragment()
    }

    private lateinit var viewModel: AddEditDiplomaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_edit_diploma_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddEditDiplomaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}