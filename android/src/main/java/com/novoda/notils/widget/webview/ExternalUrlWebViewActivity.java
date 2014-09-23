package com.novoda.notils.widget.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

/**
 * An Activity hosting a webview that can load a URL
 * Don't forget to declare in the AndroidManifest:
 * <code><activity android:name="com.novoda.notils.widget.webview.ExternalUrlWebViewActivity" /></code>
 */
public class ExternalUrlWebViewActivity extends Activity {

    public static final String EXTRA_URL = "com.novoda.notils.EXTRA_URL";
    public static final String EXTRA_TITLE = "com.novoda.notils.EXTRA_TITLE";

    private static final int PROGRESS_RATIO = 1000;

    private WebView webView;

    /**
     * This will load an Activity hosting a webview to the provided URL
     * ensure you declare me in your manifest
     * <code><activity android:name="com.novoda.notils.widget.webview.ExternalUrlWebViewActivity" /></code>
     *
     * @param context Any context
     * @param url     A valid url to navigate to
     */
    public static void toUrl(Context context, String url) {
        toUrl(context, url, android.R.string.untitled);
    }

    /**
     * This will load an Activity hosting a webview to the provided URL
     * ensure you declare me in your manifest
     * <code><activity android:name="com.novoda.notils.widget.webview.ExternalUrlWebViewActivity" /></code>
     *
     * @param context    Any context
     * @param url        A valid url to navigate to
     * @param titleResId A String resource to display as the title
     */
    public static void toUrl(Context context, String url, int titleResId) {
        toUrl(context, url, context.getString(titleResId));
    }

    /**
     * This will load an Activity hosting a webview to the provided URL
     * ensure you declare me in your manifest
     * <code><activity android:name="com.novoda.notils.widget.webview.ExternalUrlWebViewActivity" /></code>
     *
     * @param context Any context
     * @param url     A valid url to navigate to
     * @param title   A title to display
     */
    public static void toUrl(Context context, String url, String title) {
        Intent intent = new Intent(context, ExternalUrlWebViewActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getWebViewTitle());
        createWebView();
        setContentView(webView);
        enableJavascript();
        enableCaching();
        enableCustomClients();
        zoomedOut();
        webView.loadUrl(getUrl());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && canGoBackInWebViewHistory()) {
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Default implementation returns true if the webView has a history
     * Override to prevent the webView to navigate Backwards.
     * @return if the activity can navigate in webView history
     */
    protected boolean canGoBackInWebViewHistory() {
        return webView.canGoBack();
    }

    private void createWebView() {
        webView = new WebView(this);
        FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        webView.setLayoutParams(p);
    }

    private String getWebViewTitle() {
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        return title == null ? "" : title;
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
