package com.example.firebasecrud

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firebasecrud.Model.UserModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        databaseReference = FirebaseDatabase.getInstance().getReference("Shopping")

        val btnback = findViewById<Button>(R.id.btnback)
        btnback.setOnClickListener {
            finish()
        }

        val btnEnter = findViewById<Button>(R.id.btnEnter)

        btnEnter.setOnClickListener {
            saveDataInsert()
        }
    }

    private fun saveDataInsert(){

        var useName = findViewById<EditText>(R.id.editName)
        var usePhone = findViewById<EditText>(R.id.editPhone)
        var useAddress = findViewById<EditText>(R.id.editAddress)
        var useMoney = findViewById<EditText>(R.id.editMoney)

        val userName = useName.text.toString()
        val userPhone = usePhone.text.toString()
        val userAddress = useAddress.text.toString()
        val userMoney = useMoney.text.toString()


        val User = UserModel(userName, userPhone, userAddress, userMoney)

        if(userName.isEmpty()){
            useName.error = "សូមបញ្ចូលឈ្មោះ"
            return
        }else if (userPhone.isEmpty()){
            usePhone.error = "សូមបញ្ចូលលេខទូរស័ព្ទ"
            return
        }else if (userAddress.isEmpty()){
            useAddress.error = "សូមបញ្ចូលអាស័យដ្ឋាន"
            return
        }else if (userMoney.isEmpty()){
            useMoney.error = "សូមបំពេញលុយ"
            return
        }

        databaseReference.child(userName).setValue(User)
            .addOnSuccessListener {

                Toast.makeText(this,"Add success", Toast.LENGTH_SHORT).show()
                useName.text.clear()
                usePhone.text.clear()
                useAddress.text.clear()
                useMoney.text.clear()

            }.addOnFailureListener {err->

                Toast.makeText(this, "Failed${err.message}", Toast.LENGTH_SHORT).show()
            }

    }
}