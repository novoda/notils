package com.novoda.notils.widget.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * A WebView that allows you to load a html file from your /res/raw directory
 */
public class RawWebView extends WebView {

    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String TEXT_HTML = "text/html";
    private static final String ANDROID_RAW_BASE_URL = "file:///android_res/raw";
    private static final String FAIL_URL = null;

    public RawWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RawWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void loadRawData(String html) {
        loadDataWithBaseURL(ANDROID_RAW_BASE_URL, html, TEXT_HTML, DEFAULT_ENCODING, FAIL_URL);
    }

    public void loadRawUrl(String rawFileName) {
        loadUrl(ANDROID_RAW_BASE_URL + "/" + rawFileName);
    }

}
