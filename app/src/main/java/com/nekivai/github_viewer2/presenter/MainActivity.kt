package com.nekivai.github_viewer2.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nekivai.github_viewer2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}