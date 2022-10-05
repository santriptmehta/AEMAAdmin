package com.blankspace.aemaadmin.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankspace.aemaadmin.R
import com.blankspace.aemaadmin.admin_home_page
import com.blankspace.aemaadmin.homePage_admin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class login_fragment : Fragment() {

    lateinit var cadet_login_username : EditText
    lateinit var cadet_passward : EditText
    lateinit var cadet_login_button : Button
    private lateinit var auth : FirebaseAuth
    lateinit var register2 : TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        cadet_login_username = view.findViewById(R.id.cadet_username)
        cadet_passward = view.findViewById(R.id.cadet_passward)
        cadet_login_button = view.findViewById(R.id.cadet_loginbt)
        auth = Firebase.auth
        register2 = view.findViewById(R.id.register2)
        val loginProgress: ProgressBar = view.findViewById(R.id.login_progress)

        register2.setOnClickListener {
                fragmentManager?.beginTransaction()?.replace(R.id.auth_framelayout, register_fragment())
                    ?.addToBackStack(null)
                    ?.commit()

        }

        if(auth.currentUser != null){
            var intent = Intent(activity, homePage_admin::class.java)
            startActivity(intent)

        }else{
            cadet_login_button.setOnClickListener {

                val email = cadet_login_username.text.toString()
                val password = cadet_passward.text.toString()

                cadet_login_username.error = null
                cadet_passward.error = null

                if(TextUtils.isEmpty(email)){
                    cadet_login_button.error = "Email is Required"
                    return@setOnClickListener
                }
                if(TextUtils.isEmpty(password)){
                    cadet_passward.error = "Password is Required"
                    return@setOnClickListener
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    cadet_login_username.error = "Please enter a valid email address"
                    return@setOnClickListener
                }

                loginProgress.visibility = View.VISIBLE
                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener{task ->
                        loginProgress.visibility = View.GONE
                        if(task.isSuccessful){
                            var intent = Intent(activity, homePage_admin::class.java)
                            startActivity(intent)
                            Toast.makeText(activity,"successfull", Toast.LENGTH_LONG).show()
                        }else {
                            Toast.makeText(activity, "Something went wrong. Please try again.", Toast.LENGTH_LONG).show()
                        }
                    }
            }

        }

    }

}