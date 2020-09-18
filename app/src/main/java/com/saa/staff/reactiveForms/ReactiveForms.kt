package com.saa.staff.reactiveForms

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputLayout


class ReactiveForms() {}

/**
 * @param forms create an array of forms to validate
 */
class FormGroup// loop through all the forms and check if they are invalid
    (var forms: Array<Form>) {
    var isValidListener: LiveData<Boolean>

    init {
        val listener = MutableLiveData(false)
        var isValid: Boolean
        forms.forEach { form->
            form.startFormListener().observeForever{ value->
                isValid = value
                if(value) {
                    // loop through all the forms and check if they are invalid
                    for(form in forms){
                        if(form.formListener.value != null){
                            if(!form.formListener.value!!){
                                isValid = false
                                break
                            }
                        }
                    }
                }
                listener.postValue(isValid)
            }
        }
        isValidListener = listener
    }
}

class Form(private var inputLayout: TextInputLayout, private var validator: (value: String)-> Boolean){
    lateinit var formListener: LiveData<Boolean>
    fun startFormListener(): LiveData<Boolean>{
        val isValidOutput = MutableLiveData(false)
        inputLayout.editText?.addTextChangedListener {
            // if the error is not null and the form is not valid, display an error message
            val isValid = validator(it.toString())
            if(!isValid && inputLayout.error != null){
                inputLayout.error = "invalid"
            }else{
                inputLayout.error = null
            }
            isValidOutput.postValue(isValid)
        }
        formListener = isValidOutput
        return isValidOutput
    }
}

enum class ValidatorType{
    Required,
    DateTime,
    PasswordSame
}