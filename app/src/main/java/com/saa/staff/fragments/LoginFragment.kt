package com.saa.staff.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.saa.staff.R
import com.saa.staff.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        binding.emailText.editText!!.setText(viewModel.email)
        binding.passwordText.editText!!.setText(viewModel.password)
        binding.emailText.editText!!.addTextChangedListener{
            if(it.toString().isBlank()){
                if(binding.emailText.error != "Required"){
                    binding.emailText.error = "Required"
                }
            }else{
                binding.emailText.error = null
            }
        }
        binding.passwordText.editText!!.addTextChangedListener{
            if(it.toString().isBlank()){
                if(binding.passwordText.error != "Required"){
                    binding.passwordText.error = "Required"
                }
            }else{
                binding.passwordText.error = null
            }
        }
        // navigate to sign up
        binding.signupButton.setOnClickListener{
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }
    }
}