package com.android72.perludilindungi.ui.berita

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.android72.perludilindungi.R

class WebViewFragment : Fragment(R.layout.web_berita) {
    private val arguments : WebViewFragmentArgs by navArgs();
    private lateinit var webView: WebView;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.newsWeb)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return false
            }
        }
        webView.loadUrl(arguments.url);
    }
}