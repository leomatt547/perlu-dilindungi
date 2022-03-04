package com.android72.perludilindungi.ui.berita

import android.net.http.SslError
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.android72.perludilindungi.R
import com.android72.perludilindungi.databinding.WebBeritaBinding
import kotlinx.android.synthetic.main.web_berita.*

class WebViewActivity : AppCompatActivity() {
//    private val arguments : WebViewFragmentArgs by navArgs();
    private var _binding: WebBeritaBinding? = null;
    private val binding get() = _binding!!
    private lateinit var webView: WebView;

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = WebBeritaBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_berita);

        val newsUrl = intent.getStringExtra("URL_TO_WEB")!!
        Log.v("tag", newsUrl)
        webView = newsWeb
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                super.onReceivedSslError(view, handler, error)
                if (handler != null) {
                    handler.proceed()
                };
            }

            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                Toast.makeText(this@WebViewActivity, "Got Error! $error", Toast.LENGTH_SHORT).show()
            }
        }
        webView.loadUrl(newsUrl);
    }
}