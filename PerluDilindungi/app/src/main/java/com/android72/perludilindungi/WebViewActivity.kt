package com.android72.perludilindungi

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {
    private lateinit var webView: WebView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_berita);

        webView = findViewById(R.id.newsWeb);
        webView.settings.javaScriptEnabled
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl("...");
    }
}