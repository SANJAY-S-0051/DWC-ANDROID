package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.CrawlActivity
import com.example.StartActivity

class MainActivity : AppCompatActivity() {

    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the button by its ID
        startButton = findViewById(R.id.button2)

        // Set a click listener for the button
        startButton.setOnClickListener {
            intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }
    }

}

