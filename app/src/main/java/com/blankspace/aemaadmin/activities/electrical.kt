package com.blankspace.aemaadmin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankspace.aemaadmin.R
import com.blankspace.aemaadmin.adapter.electrical_adapter
import com.blankspace.aemaadmin.model.Electrical
import com.google.firebase.firestore.FirebaseFirestore

class electrical : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var userList: ArrayList<Electrical>
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electrical)

        val layoutManager = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.electrical_recycler_view)
        recyclerView.layoutManager = layoutManager
        userList = arrayListOf()
        db = FirebaseFirestore.getInstance()
        db.collection("Electrical").get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                    for(data in it.documents){
                        val userElc :  Electrical? = data.toObject(Electrical::class.java)
                        if(userElc!=null){
                            userList.add(userElc)
                        }
                    }
                    recyclerView.adapter = electrical_adapter(userList, this)
                }
            }
            .addOnFailureListener{
                Toast.makeText(this,it.toString(), Toast.LENGTH_LONG).show()
            }


    }
}