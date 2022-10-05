package com.blankspace.aemaadmin.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.blankspace.aemaadmin.R
import com.blankspace.aemaadmin.model.User
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class register_fragment : Fragment() {

    companion object {
        const val TAG = "RegisterFragment"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val emailText: TextInputLayout = view.findViewById(R.id.email_text)
        val nameText: TextInputLayout = view.findViewById(R.id.name_text)
        val passwordText: TextInputLayout = view.findViewById(R.id.password_text)
        val confirmPasswordText: TextInputLayout = view.findViewById(R.id.confirm_password_text)
        val registerButton: Button = view.findViewById(R.id.register_button)
        val registerProgress: ProgressBar = view.findViewById(R.id.register_progress)
        val loginPage : TextView = view.findViewById(R.id.go_to_login)

        loginPage.setOnClickListener {
                fragmentManager?.beginTransaction()?.replace(R.id.auth_framelayout, login_fragment())
                    ?.addToBackStack(null)
                    ?.commit()
        }
        registerButton.setOnClickListener {
            val email = emailText.editText?.text.toString()
            val name = nameText.editText?.text.toString()
            val password = passwordText.editText?.text.toString()
            val confirmPassword = confirmPasswordText.editText?.text.toString()

            emailText.error = null
            nameText.error = null
            passwordText.error = null
            confirmPasswordText.error = null

            if(TextUtils.isEmpty(name)){
                nameText.error = "Name is required"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                passwordText.error = "Password is required"
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(confirmPassword)) {
                confirmPasswordText.error = "Confirm password field is required"
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                confirmPasswordText.error = "Passwords do not match"
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailText.error = "Please enter a valid email address"
                return@setOnClickListener
            }
            registerProgress.visibility = View.VISIBLE

            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = User(auth.currentUser?.uid!!, name, email)
                        val firestore = FirebaseFirestore.getInstance().collection("Users")
                        firestore.document(auth.currentUser?.uid!!).set(user)
                            .addOnCompleteListener { task2 ->
                                registerProgress.visibility = View.GONE
                                if (task2.isSuccessful) {
                                    Toast.makeText(activity,"Successfully Account Created.", Toast.LENGTH_LONG).show()

                                } else {
                                    Toast.makeText(activity,"Something went wrong. Please try again.", Toast.LENGTH_LONG).show()
                                    Log.d(TAG, task.exception.toString())
                                }
                            }
                    } else {
                        registerProgress.visibility = View.GONE
                        Toast.makeText(activity, "Something went wrong. Please try again.", Toast.LENGTH_LONG).show()
                        Log.d(TAG, task.exception.toString())
                    }
                }

        }
    }


}