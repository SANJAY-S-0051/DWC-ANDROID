package com.example

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication1.R
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class CrawlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crawl)
        val linkEditText: EditText = findViewById(R.id.linkEditText)
        val openLinkButton: Button = findViewById(R.id.openLinkButton)
        val DisplayLinks: TextView = findViewById(R.id.textView4)

        openLinkButton.setOnClickListener {
            // Retrieve the link entered by the user
            val url: String = linkEditText.text.toString().trim()

            // Use the link as needed (e.g., open it in a web browser)
            if (url.isNotEmpty()) {
                val client = OkHttpClient()
                val requestBody = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), url)

                val formBody = FormBody.Builder()
                    .add("path", url)
                    .build()

                val request = Request.Builder()
                    .url("https://laughing-garbanzo-jgwxg6j5jj6cpgrq-8000.app.github.dev/")
                    .post(formBody)
                    .build()

                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        // Handle the error
                    }
                    override fun onResponse(call: Call, response: Response) {
                        if (!response.isSuccessful) {
                            // Handle the error
                        } else {
                            // Use the response body (and close it)
                            response.body?.let { body ->
                                runOnUiThread {
                                    // Display the response to the user
                                    val jsonResponse = JSONObject(body.string())
                                    val children = jsonResponse.getJSONArray("children")

                                    // Extract the URLs and add them to the TextView
                                    val urls = StringBuilder()
                                    for (i in 0 until children.length()) {
                                        val child = children.getJSONObject(i)
                                        urls.append(child.getString("url")).append("\n")
                                    }

                                    // Display the URLs in the TextView
                                    DisplayLinks.text = urls.toString()

                                }
                            }
                        }
                    }
                })
            } else {
                // Handle the case where the entered link is empty
                // For example, you can show a Toast message
                // Toast.makeText(this, "Please enter a valid link", Toast.LENGTH_SHORT).show()
            }
        }
    }



}

