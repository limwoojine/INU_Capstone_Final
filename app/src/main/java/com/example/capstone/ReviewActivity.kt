package com.example.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File

class ReviewActivity : AppCompatActivity() {


    lateinit var foodName: TextView
    lateinit var foodRating: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        foodName = findViewById(R.id.food_title)
        foodName.text = intent.getStringExtra("storeName")


    }
}

    /*fun setViews() {
        binding.MenuCameraBtn.setOnClickListener {
            cameraPermission.launch(Manifest.permission.CAMERA)
        }
        binding.MenuGlyBtn.setOnClickListener {
            openGallery()
        }
    }

    fun openCamera() {
        val photoFile = File.createTempFile(
            "IMG_",
            ".jpg",
            getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )

        photoUri = FileProvider.getUriForFile(
            this,
            "${packageName}.provider",
            photoFile
        )

        cameraLauncher.launch(photoUri)
    }

    fun openGallery() {
        galleryLauncher.launch("image/*")
    }*/