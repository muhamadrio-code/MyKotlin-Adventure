package com.example.test

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_second.*
import java.security.Permission

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        EXPLICIT INTENT
        val person = intent.getSerializableExtra("EXTRA_PERSON") as Person
        val name = person.name
        val age = person.age
        val country = person.country
        tvSecondActivity.text = "$name is $age years lives in $country"

//        val name = intent.getStringExtra("EXTRA_NAME")
//        val age = intent.getIntExtra("EXTRA_AGE", 0)
//        val country = intent.getStringExtra("EXTRA_COUNTRY")
//        val personString = "$name is $age years old lives in $country"
//        tvSecondActivity.text = personString

//        IMPLICIT INTENT

        btnChooseImg.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(it , 0)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            ivImage.setImageURI(uri)
        }
    }
}


