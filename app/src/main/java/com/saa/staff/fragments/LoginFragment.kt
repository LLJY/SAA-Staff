package com.saa.staff.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.saa.staff.activities.mainui.HomeActivity
import com.saa.staff.databinding.LoginFragmentBinding
import com.saa.staff.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    @Inject
    lateinit var pd: ProgressDialog
    private val viewModel: LoginViewModel by activityViewModels()
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
        val intent = Intent(activity, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // pass the userid to the next activity
        println("userid: a")
        intent.putExtra("USER_ID", "a")
        startActivity(intent)
        binding.emailText.editText!!.setText(viewModel.email)
        binding.passwordText.editText!!.setText(viewModel.password)
        binding.emailText.editText!!.addTextChangedListener {
            viewModel.email = it.toString()
            if (it.toString().isBlank()) {
                if (binding.emailText.error != "Required") {
                    binding.emailText.error = "Required"
                }
            } else {
                binding.emailText.error = null
            }
        }
        binding.passwordText.editText!!.addTextChangedListener{
            viewModel.password = it.toString()
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
        binding.loginButton.setOnClickListener{
            pd.show()
            if(binding.emailText.error == null && binding.passwordText.error == null){
                viewModel.login().observe(viewLifecycleOwner, Observer {
                    pd.dismiss()
                    if (it.isNotBlank()) {
                        val intent = Intent(activity, HomeActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        // pass the userid to the next activity
                        println("userid: $it")
                        intent.putExtra("USER_ID", it)
                        startActivity(intent)
                    } else {
                        Snackbar.make(
                            binding.root,
                            "Oops!, something went wrong",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }
    }
}