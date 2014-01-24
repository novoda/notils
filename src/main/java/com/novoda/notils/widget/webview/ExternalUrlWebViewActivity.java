package com.novoda.notils.widget.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

public class ExternalUrlWebViewActivity extends Activity {

    public static final String EXTRA_URL = "com.novoda.notils.EXTRA_URL";
    public static final String EXTRA_TITLE = "com.novoda.notils.EXTRA_TITLE";

    private static final int PROGRESS_RATIO = 1000;

    private WebView webView;

    public static void toUrl(Context context, String url) {
        toUrl(context, url, "");
    }

    public static void toUrl(Context context, String url, String title) {
        Intent intent = new Intent(context, ExternalUrlWebViewActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getTitleResId());
        createWebView();
        enableJavascript();
        enableCaching();
        enableCustomClients();
        zoomedOut();
        webView.loadUrl(getUrl());
    }

    private void createWebView() {
        webView = new WebView(this);
        FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        webView.setLayoutParams(p);
    }

    private int getTitleResId() {
        return getIntent().getIntExtra(EXTRA_TITLE, 0);
    }

    private void enableCustomClients() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                setProgress(progress * PROGRESS_RATIO);
            }
        });
    }

    private void enableJavascript() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }

    private void enableCaching() {
        webView.getSettings().setAppCachePath(getFilesDir() + getPackageName() + "/cache");
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
    }

    private void zoomedOut() {
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
    }

    private String getUrl() {
        return getIntent().getStringExtra(EXTRA_URL);
    }
}
