package com.blankspace.aemaadmin.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.blankspace.aemaadmin.R

class auth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        supportFragmentManager.beginTransaction()
            .replace(R.id.auth_framelayout,login_fragment())
            .commit()
    }
}