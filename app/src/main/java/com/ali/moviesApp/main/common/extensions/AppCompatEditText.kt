

package com.ali.moviesApp.main.common.extensions

import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.widget.CheckBox
import androidx.appcompat.widget.AppCompatEditText


fun AppCompatEditText.applyPhoneNumberFilters(regexPattern: Regex, maxLength: Int? = null) {
    maxLength?.let {
        filters = arrayOf(android.text.InputFilter.LengthFilter(it))
    }
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val inputText = charSequence.toString()
            if (!inputText.isNullOrEmpty()) {
                if (!regexPattern.matches(inputText)) {
                    setText("")
                }
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    })
}







fun AppCompatEditText.setupPasswordToggleCheckbox(passwordToggleCheckbox: CheckBox) {
    passwordToggleCheckbox.setOnCheckedChangeListener { compoundButton, isChecked ->
        if (compoundButton.isPressed) {
            transformationMethod = if (isChecked) null else PasswordTransformationMethod()
        }

    }
}

