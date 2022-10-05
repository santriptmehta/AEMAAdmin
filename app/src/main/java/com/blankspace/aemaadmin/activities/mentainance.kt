package com.blankspace.aemaadmin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankspace.aemaadmin.R
import com.blankspace.aemaadmin.adapter.electrical_adapter
import com.blankspace.aemaadmin.adapter.maintainance_adapter
import com.blankspace.aemaadmin.model.Electrical
import com.blankspace.aemaadmin.model.Maintainance
import com.google.firebase.firestore.FirebaseFirestore

class mentainance : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var userList: ArrayList<Maintainance>
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentainance)

        val layoutManager = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.maintainance_recycler_view)
        recyclerView.layoutManager = layoutManager
        userList = arrayListOf()
        db = FirebaseFirestore.getInstance()
        db.collection("Maintainance").get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                    for(data in it.documents){
                        val userElc :  Maintainance? = data.toObject(Maintainance::class.java)
                        if(userElc!=null){
                            userList.add(userElc)
                        }
                    }
                    recyclerView.adapter = maintainance_adapter(userList, this)
                }
            }
            .addOnFailureListener{
                Toast.makeText(this,it.toString(), Toast.LENGTH_LONG).show()
            }


    }
}