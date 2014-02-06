package com.novoda.notils.widget.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

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

    /**
     * Using this method with Proguard may result it missing file behaviour.
     * Recommended {@link #loadRawResource(int)} instead.
     * @param rawFileName a filename in your /raw/ directory i.e. "novoda.html"
     */
    public void loadRawUrl(String rawFileName) {
        loadUrl(ANDROID_RAW_BASE_URL + "/" + rawFileName);
    }

    /**
     *
     * @param rawResourceId the corresponding id of a filename in your /raw/ directory i.e. "R.raw.novoda"
     */
    public void loadRawResource(int rawResourceId) {
        InputStream input = null;
        try {
            input = getResources().openRawResource(rawResourceId);
            String html = loadFrom(input);
            loadRawData(html);
        } finally {
            tryClose(input);
        }
    }

    private String loadFrom(InputStream input) {
        Scanner scanner = new Scanner(input, DEFAULT_ENCODING).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    private void tryClose(Closeable input) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
