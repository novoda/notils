package com.novoda.notils.logger.toast;

public interface ToastDisplayer {

    void display(String message);

    void display(int resId);

    void displayLong(String message);

    void displayLong(int resId);

    void cancelAll();

}
