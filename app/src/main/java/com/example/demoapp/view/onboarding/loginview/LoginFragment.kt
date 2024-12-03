package com.example.demoapp.view.onboarding.loginview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputLayout
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.demoapp.R
import com.example.demoapp.view.homeandactionmenu.HomeAndActionMenuActivity

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val emailInputLayout: TextInputLayout = view.findViewById(R.id.tfEmail)
        val passwordInputLayout: TextInputLayout = view.findViewById(R.id.tfPassword)
        val signInButton: Button = view.findViewById(R.id.btnSignIn)
        val checkbox: CheckBox = view.findViewById(R.id.myCheckbox)

        signInButton.setOnClickListener {
            val email = emailInputLayout.editText?.text.toString().trim()
            val password = passwordInputLayout.editText?.text.toString().trim()

            if (validateInput(email, password, emailInputLayout, passwordInputLayout)) {
                checkbox.setOnClickListener {
                    if (checkbox.isChecked) {
                        val sharedPref = activity?.getSharedPreferences(
                            getString(R.string.str_preference_file_key),
                            Context.MODE_PRIVATE
                        )
                        val editor = sharedPref?.edit()
                        editor?.apply {
                            putBoolean("isLoggedIn", true)
                            apply()
                        }
                    }
                }
                val intent = Intent(requireContext(), HomeAndActionMenuActivity::class.java)
                startActivity(intent)
            }
        }

        val backArrow = view.findViewById<ImageView>(R.id.left_arrow)
        backArrow.setOnClickListener{
            parentFragmentManager.popBackStack()
        }

        return view
    }

    private fun validateInput(
        email: String,
        password: String,
        emailInputLayout: TextInputLayout,
        passwordInputLayout: TextInputLayout
    ): Boolean {
        var isValid = true

        // Validate email
        if (email.isEmpty()) {
            emailInputLayout.error = "Email cannot be empty"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInputLayout.error = "Invalid email format"
            isValid = false
        } else {
            emailInputLayout.error = null
        }

        // Validate password
        if (password.isEmpty()) {
            passwordInputLayout.error = "Password cannot be empty"
            isValid = false
        } else if (password.length < 6) {
            passwordInputLayout.error = "Password must be at least 6 characters"
            isValid = false
        } else {
            passwordInputLayout.error = null
        }

        return isValid
    }
}
