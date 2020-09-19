package com.saa.staff.activities.mainui.SendNotification

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.saa.staff.R
import com.saa.staff.databinding.SendNotificationFragmentBinding
import javax.inject.Inject

class SendNotificationFragment : Fragment() {
    val viewModel: SendNotificationViewModel by viewModels()
    lateinit var binding: SendNotificationFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SendNotificationFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}