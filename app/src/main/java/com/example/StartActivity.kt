package com.example

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication1.R
import java.net.URL

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start2)
        val buttonTor = findViewById<Button>(R.id.TorButton)
        buttonTor.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.torproject.org/download/")
            startActivity(openURL)
        }
            val buttonCrawl= findViewById<Button>(R.id.button10)
            buttonCrawl.setOnClickListener {
                intent = Intent(this, CrawlActivity::class.java)
                startActivity(intent)
            }
        }
    }
