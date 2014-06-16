package com.novoda.notils.logger.toast;

import android.widget.Toast;

public interface ToastDisplayer {

    Toast display(String message);

    Toast display(int resId);

    Toast displayLong(String message);

    Toast displayLong(int resId);

    void cancelAll();

}
