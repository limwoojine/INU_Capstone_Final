package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val foodName: TextView = findViewById(R.id.food_title)
        if (intent.hasExtra("name")) {
            foodName.text = intent.getStringExtra("name")
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }

    }
}