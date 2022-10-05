package com.blankspace.aemaadmin

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.blankspace.aemaadmin.activities.electrical
import com.blankspace.aemaadmin.activities.mentainance
import com.blankspace.aemaadmin.activities.plumbing
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class homePage_admin : AppCompatActivity() {
    lateinit var fauth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_admin)

        val user_name_text : TextView = findViewById(R.id.user_text)
        user_name_text.text = "Hello Unknown"
        val db = FirebaseFirestore.getInstance()
        fauth = FirebaseAuth.getInstance()
        val userId = fauth.currentUser?.uid
        val userRef = db.collection("Users")
        if (userId != null) {
            userRef.document(userId).get().addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val document = task.result
                    if(document.exists()){
                        user_name_text.text = "Administrative Login By ${document.getString("name")}"
                        Log.d(ContentValues.TAG,"${document.getString("name")}")
                    }else{
                        Log.d(ContentValues.TAG, "This does not exit")
                    }
                }else{
                    task.exception?.message?.let {
                        Log.d(ContentValues.TAG,it)
                    }
                }
            }
        }

    }


    fun view_plum_frag(view: View) {
        val intent = Intent(this, mentainance::class.java)
        startActivity(intent)
    }
    fun view_maint_frag(view: View) {
        val intent = Intent(this, plumbing::class.java)
        startActivity(intent)
    }
    fun view_elec_frag(view: View) {
        val intent = Intent(this, electrical::class.java)
        startActivity(intent)
    }

    fun logOut(view: View) {
        fauth = Firebase.auth
        Firebase.auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}