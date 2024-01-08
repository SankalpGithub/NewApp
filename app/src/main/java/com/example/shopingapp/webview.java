package com.example.shopingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        // missing 'http://' will cause crashed
        Bundle extras = getIntent().getExtras();
        String uri = extras.getString("url");
        WebView webView = findViewById(R.id.webview);
        webView.setVisibility(View.VISIBLE);
        // loading http://www.google.com url in the WebView.
        webView.loadUrl(uri);

        // this will enable the javascript.
        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient());
    }
}