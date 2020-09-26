package com.saa.staff.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.saa.staff.R
import com.saa.staff.databinding.FragmentSignUpBinding
import com.saa.staff.viewModels.LoginViewModel
import com.saa.staff.viewModels.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Fragment for signup. can be expanded in the future.
 */
@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignupViewModel by viewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()
    @Inject
    lateinit var pd: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            activity?.onBackPressed()
        }
        // begin bind views

        val userLevels = listOf("School Head", "Course Manager", "Admin")
        val userLevelAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            userLevels
        )
        // get all the countries by ISO
        val countriesISO = Locale.getISOCountries()
        val countries = ArrayList<String>()
        // HACK, create a locale for the country's ISO, then get the full country name and add it to the list
        countriesISO.forEach {
            val locale = Locale("",it)
            countries.add(locale.getDisplayCountry(locale))
        }
        val countriesAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, countries)
        (binding.countrySpinner.editText!! as AutoCompleteTextView).setAdapter(countriesAdapter)
        (binding.roleSpinner.editText!! as AutoCompleteTextView).setAdapter(userLevelAdapter)
        lifecycleScope.launch(Dispatchers.Main) {


            binding.dobPicker.setEndIconOnClickListener {
                lifecycleScope.launch(Dispatchers.Main) {
                    val picker = MaterialDatePicker.Builder.datePicker().build()
                    activity?.supportFragmentManager?.let { it1 ->
                        picker.show(
                            it1,
                            picker.toString()
                        )
                    }
                    picker.addOnPositiveButtonClickListener { date ->
                        viewModel.user.dateOfBirth = date
                        val dateString = SimpleDateFormat("dd/MM/yyyy").format(date)
                        binding.dobPicker.editText!!.setText(dateString!!)

                    }
                }
            }
            binding.passportExpiryPicker.setEndIconOnClickListener {
                lifecycleScope.launch(Dispatchers.Main) {
                    val picker = MaterialDatePicker.Builder.datePicker().build()
                    activity?.supportFragmentManager?.let { it1 ->
                        picker.show(
                            it1,
                            picker.toString()
                        )
                    }
                    picker.addOnPositiveButtonClickListener { date ->
                        viewModel.user.dateOfBirth = date
                        val dateString = SimpleDateFormat("dd/MM/yyyy").format(date)
                        binding.passportExpiryPicker.editText!!.setText(dateString!!)

                    }
                }
            }
            binding.roleSpinner.editText!!.addTextChangedListener {
                binding.roleSpinner.error = null
                viewModel.user.userLevel = userLevels.indexOf(it!!.toString()) + 1
            }
            binding.countrySpinner.editText!!.addTextChangedListener {
                binding.countrySpinner.error = null
                viewModel.user.country = it!!.toString()
            }
            binding.emailText.editText!!.addTextChangedListener {
                binding.emailText.error = null
                viewModel.user.email = it.toString()
            }
            binding.firstNameText.editText!!.addTextChangedListener {
                binding.firstNameText.error = null
                viewModel.user.firstName = it.toString()
            }
            binding.middleNameText.editText!!.addTextChangedListener {
                binding.middleNameText.error = null
                viewModel.user.middleName = it.toString()
            }
            binding.lastNameText.editText!!.addTextChangedListener {
                binding.lastNameText.error = null
                viewModel.user.lastName = it.toString()
            }
            binding.dobPicker.editText!!.addTextChangedListener {
                try {
                    val date = SimpleDateFormat("dd/MM/yyyy").parse(it.toString())
                    viewModel.user.dateOfBirth = date.time
                    binding.dobPicker.error = null
                } catch (ex: Exception) {
                    markFormError(binding.dobPicker, "Please Enter a Valid Date!")
                }
            }
            binding.passportExpiryPicker.editText!!.addTextChangedListener {
                try {
                    val date = SimpleDateFormat("dd/MM/yyyy").parse(it.toString())
                    viewModel.user.passportExpiry = date.time
                    binding.passportExpiryPicker.error = null
                } catch (ex: Exception) {
                    markFormError(binding.passportExpiryPicker, "Please Enter a Valid Date!")
                }
            }
            binding.passportNumberText.editText!!.addTextChangedListener {
                binding.passportNumberText.error = null
                viewModel.user.passportNumber = it.toString()
            }
            binding.contactNumberText.editText!!.addTextChangedListener {
                binding.contactNumberText.error = null
                viewModel.user.contactNumber = it.toString().toInt()
            }
            binding.passwordText.editText!!.addTextChangedListener {
                binding.passwordText.error = null
                binding.passwordAgainText.error = null
                viewModel.user.password = it.toString()
            }
            binding.passwordAgainText.editText!!.addTextChangedListener {
                binding.passwordText.error = null
                binding.passwordAgainText.error = null
                if (viewModel.user.password != it.toString()) {
                    markFormError(binding.passwordText, "Password Must Match!")
                    markFormError(binding.passwordAgainText, "Password Must Match!")
                }
            }
            binding.doneButton.setOnClickListener {

                if (!(checkFieldEmpty(binding.roleSpinner) && checkFieldEmpty(binding.countrySpinner) && checkFieldEmpty(
                        binding.emailText
                    ) && checkFieldEmpty(
                        binding.firstNameText
                    ) && checkFieldEmpty(binding.lastNameText) && checkFieldEmpty(binding.dobPicker) && checkFieldEmpty(
                        binding.passportNumberText
                    ) && checkFieldEmpty(binding.passportExpiryPicker) && checkFieldEmpty(
                        binding.contactNumberText
                    ) && checkFieldEmpty(binding.passwordText) && checkFieldEmpty(binding.passwordAgainText))
                ) {
                    pd.show()
                    viewModel.signUp().observe(viewLifecycleOwner) {
                        pd.dismiss()
                        if(it){
                            activity?.onBackPressed()
                            // autofill email for the user.
                            loginViewModel.email = viewModel.user.email
                        }else{
                            Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Snackbar.make(view, "Ensure all fields are valid", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    /**
     * checks if the error is present on a field before marking it, prevents the annoying
     * blinking effect. Mostly eyecandy.
     */
    fun markFormError(inputLayout: TextInputLayout, errorMessage: String) {
        if (inputLayout.error != errorMessage) {
            inputLayout.error = errorMessage
        }
    }

    /**
     * checks if the field is empty and marks a required error on it
     */
    fun checkFieldEmpty(inputLayout: TextInputLayout): Boolean {
        val isBlank = inputLayout.editText!!.text.isBlank()
        if (isBlank) {
            inputLayout.error = "Required!"
        }
        return isBlank
    }

}