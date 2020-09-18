package com.saa.staff.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.saa.staff.R
import com.saa.staff.viewModels.SendNotificationViewModel

class SendNotificationFragment : Fragment() {

    companion object {
        fun newInstance() = SendNotificationFragment()
    }

    private lateinit var viewModel: SendNotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.send_notification_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SendNotificationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}