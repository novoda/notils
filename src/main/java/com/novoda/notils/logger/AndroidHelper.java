package com.novoda.notils.logger;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

public final class AndroidHelper {

    public static final class AppName {
        public static String fromContext(Context context) {
            final PackageManager pm = context.getApplicationContext().getPackageManager();
            try {
                ApplicationInfo ai = pm.getApplicationInfo(context.getPackageName(), 0);
                return (String) pm.getApplicationLabel(ai);
            } catch (PackageManager.NameNotFoundException ignored) {
                return null;
            }
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
