package com.example.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.commonmanager.utils.PrefManager
import com.example.commonmanager.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}