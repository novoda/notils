package com.novoda.notils.logger;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

public final class AndroidHelper {

    public static final class AppName {
        public static String fromContext(Context context) {
            final PackageManager pm = context.getApplicationContext().getPackageManager();
            ApplicationInfo ai;
            try {
                ai = pm.getApplicationInfo(context.getPackageName(), 0);
            } catch (final PackageManager.NameNotFoundException e) {
                ai = null;
            }
            return ai != null ? (String) pm.getApplicationLabel(ai) : null;
        }
    }

    public static boolean isAndroid() {
        try {
            Class.forName("android.os.Build");
            return Build.VERSION.SDK_INT != 0;
        } catch (ClassNotFoundException ignored) {
            return false;
        }

    }
}
