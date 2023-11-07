package com.example

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication1.R
import java.io.BufferedOutputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL


class CrawlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crawl)
        val linkEditText: EditText = findViewById(R.id.linkEditText)
        val openLinkButton: Button = findViewById(R.id.openLinkButton)

        openLinkButton.setOnClickListener {
            // Retrieve the link entered by the user
            val url: String = linkEditText.text.toString().trim()

            // Use the link as needed (e.g., open it in a web browser)
            if (url.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            } else {
                // Handle the case where the entered link is empty
                // For example, you can show a Toast message
                // Toast.makeText(this, "Please enter a valid link", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private class PostDataTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String): String {
            val postUrl = params[0]
            val postData = params[1]

            try {
                val url = URL(postUrl)
                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "POST"
                urlConnection.doOutput = true

                // Write the data to the OutputStream
                val outputStream: OutputStream = BufferedOutputStream(urlConnection.outputStream)
                outputStream.write(postData.toByteArray())
                outputStream.flush()

                // Get the response from the server
                val responseCode = urlConnection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Read the response if needed
                    // val response = urlConnection.inputStream.bufferedReader().use { it.readText() }
                    return "POST request successful"
                } else {
                    return "POST request failed, Response Code: $responseCode"
                }
            } catch (e: Exception) {
                return "Error: ${e.message}"
            }
        }

        override fun onPostExecute(result: String) {
            // Handle the result on the UI thread (e.g., display a Toast message)
            Toast.makeText(CrawlActivity(), result, Toast.LENGTH_SHORT).show()
        }
    }
}
