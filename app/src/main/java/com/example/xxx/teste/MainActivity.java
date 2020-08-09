package com.example.xxx.teste;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);

        webView.loadUrl("https://kaoyuchung.github.io/medicina/");

        webView.getSettings().setJavaScriptEnabled(true);

        final ProgressBar processo = findViewById(R.id.processo);
        processo.setVisibility(View.INVISIBLE);
        webView.setWebViewClient(new WebViewClient(){
           @Override
           public void onPageStarted(WebView view, String url, Bitmap favicon){
                super.onPageStarted(view, url, favicon);
                processo.setVisibility(View.VISIBLE);
           }
            @Override
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view, url);
                processo.setVisibility(View.INVISIBLE);
            }

            @RequiresApi(api= Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
               if(request.getUrl().getHost().equals("tiagoaguiar.co")){
                    return false;
               }

               Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
               startActivity(intent);
               return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                if(Uri.parse(url).getHost().equals("tiagoaguiar.co")){
                    return false;
                }

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
        });


    }
}
