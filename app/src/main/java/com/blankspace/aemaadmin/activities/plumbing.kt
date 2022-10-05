package com.blankspace.aemaadmin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankspace.aemaadmin.R
import com.blankspace.aemaadmin.adapter.maintainance_adapter
import com.blankspace.aemaadmin.adapter.plumbing_adapter
import com.blankspace.aemaadmin.model.Electrical
import com.blankspace.aemaadmin.model.Maintainance
import com.blankspace.aemaadmin.model.Plumbing
import com.google.firebase.firestore.FirebaseFirestore

class plumbing : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var userList: ArrayList<Plumbing>
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plumbing)


        val layoutManager = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.plumbing_recycler_view)
        recyclerView.layoutManager = layoutManager
        userList = arrayListOf()
        db = FirebaseFirestore.getInstance()
        db.collection("Plumbing").get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                    for(data in it.documents){
                        val userElc :  Plumbing? = data.toObject(Plumbing::class.java)
                        if(userElc!=null){
                            userList.add(userElc)
                        }
                    }
                    recyclerView.adapter = plumbing_adapter(userList, this)
                }
            }
            .addOnFailureListener{
                Toast.makeText(this,it.toString(), Toast.LENGTH_LONG).show()
            }
    }
}