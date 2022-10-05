package com.blankspace.aemaadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.blankspace.aemaadmin.auth.auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var Fauth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Fauth = Firebase.auth
        if(Fauth.currentUser != null){
            var intent = Intent(this, homePage_admin::class.java)
            startActivity(intent)
            finish()
        }
    }


    fun admin_login(view: View) {
        val intent = Intent(this, auth::class.java)
        startActivity(intent)
    }

}