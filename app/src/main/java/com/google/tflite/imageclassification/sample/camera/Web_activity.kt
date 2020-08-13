package com.google.tflite.imageclassification.sample.camera

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.tflite.imageclassification.sample.R

class Web_activity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_activity)

        webCall()

    }

    private fun webCall(){

        var data=intent
        var d=data.extras?.getString("value")


        webView = findViewById(R.id.webview)
        webView.settings.javaScriptEnabled=true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return false
            }
        }

        webView.loadUrl("https://www.$d.com/")
    }
}
