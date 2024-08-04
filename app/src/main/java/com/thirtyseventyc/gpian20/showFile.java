package com.thirtyseventyc.gpian20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.gpian20.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class showFile extends AppCompatActivity {

    WebView webView;

    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_file);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        statusbarcolor();
        webView = findViewById(R.id.showeb);
        Intent fromWeb = getIntent();
        String url = fromWeb.getStringExtra("url");

        webView.getSettings().setJavaScriptEnabled(true);
        webView. getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        try {
            webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + URLEncoder.encode(url, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // Check if the page is blank
                if (TextUtils.isEmpty(view.getTitle())) {
                    // The page is blank, reload the WebView
                    view.reload();
                }
            }
        });

    }

    public void statusbarcolor()
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            getWindow().setStatusBarColor(getResources().getColor(R.color.lighyB, this.getTheme()));
        } else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.lighyB));

        }
    }


}