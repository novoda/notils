package com.novoda.notils.viewserver;

import android.app.Activity;
import android.view.View;

import java.io.IOException;

final class NoActionViewServer extends ViewServer {

    NoActionViewServer() {
        super();
    }

    @Override
    public boolean start() throws IOException {
        return false;
    }

    @Override
    public boolean stop() {
        return false;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void addWindow(Activity activity) {
    }

    @Override
    public void removeWindow(Activity activity) {
    }

    @Override
    public void addWindow(View view, String name) {
    }

    @Override
    public void removeWindow(View view) {
    }

    @Override
    public void setFocusedWindow(Activity activity) {
    }

    @Override
    public void setFocusedWindow(View view) {
    }

    @Override
    public void run() {
    }

}
