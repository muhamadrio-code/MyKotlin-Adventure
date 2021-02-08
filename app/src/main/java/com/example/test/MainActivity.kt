package com.example.test

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toast.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      TODOLIST ===================================================================
//      ==========================================================================
        btnTodoList.setOnClickListener {
            Intent(this,ThirdActivity::class.java).also {
                startActivity(it)
            }
        }

//      FRAGMENT===================================================================
//      ===========================================================================

        btnFragment.setOnClickListener {
            Intent(this,FourthActivity::class.java).also {
                startActivity(it)
            }
        }

//      INTENT ===================================================================
//      ==========================================================================
        btnAddTodo.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString().toInt()
            val country = etCountry.text.toString()
            val person = Person(name, age, country)
                Intent(this,SecondActivity::class.java).also {
                    it.putExtra("EXTRA_PERSON", person)
//                it.putExtra("EXTRA_NAME", name)
//                it.putExtra("EXTRA_AGE", age)
//                it.putExtra("EXTRA_COUNTRY", country)
                    startActivity(it)
                }

//      TOAST==================================================================================
////    =======================================================================================
            Toast(this).apply {
                duration = Toast.LENGTH_SHORT
                view = layoutInflater.inflate(R.layout.custom_toast, clToastTest)
                setGravity(Gravity.BOTTOM, 0, 100)
                show()
            }
        }


//  ALERT DIALOG==============================================================================
//  ==========================================================================================
        val addContactDialog = AlertDialog.Builder(this)
            .setTitle("Add Contact")
            .setMessage("Do you want to add Mr Boo to your contact ?")
            .setPositiveButton("Yes"){_,_->
                Toast.makeText(this,"You added Mr Boo to your contact", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No"){_,_->
                Toast.makeText(this,"You didn't add Mr Boo to your contact", Toast.LENGTH_SHORT).show()
            }.create()
        btnDialog1.setOnClickListener {
            addContactDialog.show()
        }

        val options = arrayOf("First Item", "Second Item", "Third Item")
        val singleOptionDialog = AlertDialog.Builder(this)
            .setTitle("Choose your favorite item")
            .setSingleChoiceItems(options,0){dialog, i ->
                Toast.makeText(this,"You choose ${options[i]}", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Accept"){_,_->
                Toast.makeText(this,"You Accept this singleChoiceItem", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Decline"){_,_->
                Toast.makeText(this,"You Decline this singleChoiceItem", Toast.LENGTH_SHORT).show()
            }.create()
        btnDialog2.setOnClickListener {
            singleOptionDialog.show()
        }

        val multiOptionDialog = AlertDialog.Builder(this)
            .setTitle("Choose your favorite item")
            .setMultiChoiceItems(options,
                booleanArrayOf(false, false, false)){ _, i, isChecked ->
                if (isChecked){
                    Toast.makeText(this,"You Checked this ${options[i]}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,"You UnChecked this multiChoiceItem", Toast.LENGTH_SHORT).show()
                }
            }
            .setPositiveButton("Accept"){_,_->
                Toast.makeText(this,"You Accept this singleChoiceItem", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Decline"){_,_->
                Toast.makeText(this,"You Decline this singleChoiceItem", Toast.LENGTH_SHORT).show()
            }.create()
        btnDialog3.setOnClickListener {
            multiOptionDialog.show()
        }

//      SPINNER================================================================================
//      =======================================================================================
        val customList = listOf<String>("2016","2017","2018","2019","2020")
        val adapter = ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,customList)
        spCustom.adapter = adapter

        spMonths.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               Toast.makeText(this@MainActivity,
                   "You selected ${parent?.getItemAtPosition(position).toString()}",
                   Toast.LENGTH_LONG).show()
            }
        }



        btnGetPermissions.setOnClickListener {
            requestPermission()
        }
    }

//      PERMISSIONS REQUEST ===================================================================
//      =======================================================================================
    private fun hasWriteExsternalStoragePermission() =
        ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    private fun hasBackgroundLocationPermission() =
        ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun hasForegroundLocationPermission() =
        ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission(){
        var permissionToRequest = mutableListOf<String>()
        if (!hasWriteExsternalStoragePermission()){
            permissionToRequest.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!hasBackgroundLocationPermission()){
            permissionToRequest.add(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
        if (!hasForegroundLocationPermission()){
            permissionToRequest.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        if(permissionToRequest.isNotEmpty()){
            ActivityCompat.requestPermissions(this, permissionToRequest.toTypedArray(),0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Permission Request:", "${permissions[i]} granted")

                }
            }
        }
    }
//  MENU BAR ==============================================================================
//  =======================================================================================
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.imSetting -> Toast.makeText(this,"Setting is Clicked" ,Toast.LENGTH_SHORT).show()
            R.id.imFavorite -> Toast.makeText(this,"Favorite is Clicked" ,Toast.LENGTH_SHORT).show()
            R.id.imAddContact -> Toast.makeText(this,"Add Contact is Clicked" ,Toast.LENGTH_SHORT).show()
            R.id.imClose -> finish()
            R.id.imFeedback -> Toast.makeText(this,"Feedback is Clicked" ,Toast.LENGTH_SHORT).show()
        }
        return true
    }


}