package com.example.letsgocount.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.letsgocount.R
import com.example.letsgocount.data.GameRepositoryImpl
import com.example.letsgocount.domain.usecases.UseCaseGenerateQuestion


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }
}