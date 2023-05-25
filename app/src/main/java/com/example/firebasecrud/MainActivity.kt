package com.example.firebasecrud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnImage = findViewById<ImageView>(R.id.imageButton)

        var btnList = findViewById<ImageView>(R.id.imageList)

        btnImage.setOnClickListener {
            startActivity(Intent(this, InsertActivity::class.java))
        }
        btnList.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

    }
}