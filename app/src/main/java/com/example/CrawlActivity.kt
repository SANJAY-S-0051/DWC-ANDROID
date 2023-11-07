package com.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.myapplication1.R

class CrawlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crawl)
        val myWebView: WebView = findViewById<WebView>(R.id.webview)
        myWebView.loadUrl("https://laughing-garbanzo-jgwxg6j5jj6cpgrq.github.dev/")

        // Set a WebViewClient to handle clicks within the WebView
        myWebView.webViewClient = WebViewClient()
    }
}
