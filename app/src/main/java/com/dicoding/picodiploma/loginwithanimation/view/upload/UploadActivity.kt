package com.dicoding.picodiploma.loginwithanimation.view.upload

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.picodiploma.loginwithanimation.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private var currentImageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private val galleryLaunch = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
        
    }
}