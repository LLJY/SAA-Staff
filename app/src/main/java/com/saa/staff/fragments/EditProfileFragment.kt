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
import androidx.lifecycle.lifecycleScope
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.saa.staff.R
import com.saa.staff.activities.mainui.EditProfile.EditProfileViewModel
import com.saa.staff.databinding.EditProfileFragmentBinding
import com.saa.staff.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class EditProfileFragment : Fragment() {
    val viewModel: EditProfileViewModel by activityViewModels()
    val homeViewModel: HomeViewModel by activityViewModels()
    lateinit var binding: EditProfileFragmentBinding

    @Inject
    lateinit var pd: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditProfileFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //get user info
        pd.show() // show progress dialog
        println("home viewmodel id: ${homeViewModel.userId}")
        viewModel.getUserInfo(homeViewModel.userId).observe(viewLifecycleOwner) { user ->
            pd.dismiss()
            viewModel.user = user
            // begin bind views after getting user info
            (binding.countrySpinner.editText!! as AutoCompleteTextView).setText(
                user.country,
                false
            ) // do not filter
            binding.emailText.editText!!.setText(user.email)
            binding.firstNameText.editText!!.setText(user.firstName)
            binding.middleNameText.editText!!.setText(user.middleName)
            binding.lastNameText.editText!!.setText(user.lastName)
            val dateBindingString = SimpleDateFormat("dd/MM/yyyy").format(user.dateOfBirth)
            binding.dobPicker.editText!!.setText(dateBindingString)
            binding.passportNumberText.editText!!.setText(user.passportNumber)
            val passportBindingString = SimpleDateFormat("dd/MM/yyyy").format(user.passportExpiry)
            binding.passportExpiryPicker.editText!!.setText(passportBindingString)
            binding.contactNumberText.editText!!.setText(user.contactNumber.toString())
            // get all the countries by ISO
            val countriesISO = Locale.getISOCountries()
            val countries = ArrayList<String>()
            // HACK, create a locale for the country's ISO, then get the full country name and add it to the list
            countriesISO.forEach {
                val locale = Locale("", it)
                countries.add(locale.getDisplayCountry(locale))
            }
            val countriesAdapter =
                ArrayAdapter(
                    requireContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    countries
                )
            (binding.countrySpinner.editText!! as AutoCompleteTextView).setAdapter(countriesAdapter)
            // launch setting things up in a coroutine, so that everything would run asynchronously and lag a little bit less
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

                    if (!(checkFieldEmpty(binding.countrySpinner) && checkFieldEmpty(
                            binding.emailText
                        ) && checkFieldEmpty(
                            binding.firstNameText
                        ) && checkFieldEmpty(binding.lastNameText) && checkFieldEmpty(binding.dobPicker) && checkFieldEmpty(
                            binding.passportNumberText
                        ) && checkFieldEmpty(binding.passportExpiryPicker) && checkFieldEmpty(
                            binding.contactNumberText
                        ))
                    ) {
                        pd.show()
                        viewModel.updateUser().observe(viewLifecycleOwner) {
                            pd.dismiss()
                            if (it) {
                                Snackbar.make(
                                    view,
                                    "Updated user information!",
                                    Snackbar.LENGTH_LONG
                                ).show()
                                // clear the viewmodel when we are done
                                viewModel.clearViewModel()
                                requireActivity().onBackPressed()
                            } else {
                                Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_LONG)
                                    .show()
                            }
                        }
                    } else {
                        Snackbar.make(view, "Ensure all fields are valid", Snackbar.LENGTH_LONG)
                            .show()
                    }
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