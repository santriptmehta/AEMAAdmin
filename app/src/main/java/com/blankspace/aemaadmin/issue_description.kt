package com.blankspace.aemaadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class issue_description : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue_description)

        val issue_title : TextView = findViewById(R.id.issue_title)
        val issue_hostel : TextView = findViewById(R.id.issue_hotelno)
        val issue_location : TextView = findViewById(R.id.issue_locationNo)
        val issue_rollno : TextView = findViewById(R.id.issue_rollNo)
        val issue_description : TextView = findViewById(R.id.issue_description)


        val authorEmail = intent.getStringExtra("author_email")
        val author_name = intent.getStringExtra("author_name")
        val author_hostel_no = intent.getStringExtra("author_hostel_no")
        val description = intent.getStringExtra("description")
        val authorRoll = intent.getStringExtra("author_roll")
        val authro_issue_title = intent.getStringExtra("author_issue_title")
        val author_location = intent.getStringExtra("author_location")

        issue_title.text = "Issue Title :- ${authro_issue_title}"
        issue_hostel.text = "Hostel No :-${ author_hostel_no }"
        issue_location.text = "Location :-${ author_location }"
        issue_rollno.text = "Roll Np :-${ authorRoll }"
        issue_description.text = "Description :- ${ description }"

    }
}