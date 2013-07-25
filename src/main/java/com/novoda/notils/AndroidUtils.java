package com.novoda.notils;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * If your UTIL method is over 3 lines long it is NOT a util, do not put it here I will find you
 */
public final class AndroidUtils {

    private AndroidUtils() {
    }

    /**
     * Toggles the SoftKeyboard Input be careful where you call this from as if you want to
     * hide the keyboard and its already hidden it will be shown
     *
     * @param context the context / usually the activity that the view is being shown within
     */
    public static void toggleKeyboard(Context context) {
        InputMethodManager imm = ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));
        imm.toggleSoftInput(0, 0);
    }

    /**
     * Hides the SoftKeyboard input careful as if you pass a view that didn't open the
     * soft-keyboard it will ignore this call and not close
     *
     * @param context the context / usually the activity that the view is being shown within
     * @param v       the view that opened the soft-keyboard
     */
    public static void requestHideKeyboard(Context context, View v) {
        InputMethodManager imm = ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE));
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * Converts the number in pixels to the number in dips
     */
    public static int convertToDip(DisplayMetrics displayMetrics, int sizeInPixels) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInPixels, displayMetrics);
    }

    /**
     * Converts the number in dips to the number in pixels
     */
    public static int convertToPix(float density, int sizeInDips) {
        float size = sizeInDips * density;
        return (int) size;
    }

    /**
     * Returns the version name of the current app
     *
     * @param context an app context to retrieve the package manager and package name
     * @return the version name or "unknown" if the package name (of this current app) is not found
     */
    public static String getVersionName(Context context) {
        String versionName = "unknown";
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES).versionName;
        } catch (PackageManager.NameNotFoundException ignore) {
            Log.d("NoTils", "Version name not found, Paul has a hat to eat.", ignore);
        }
        return versionName;
    }

    public static int deviceDPI(Context c) {
        return c.getResources().getDisplayMetrics().densityDpi;
    }

    public static String deviceResolution(Context c) {
        DisplayMetrics metrics = c.getResources().getDisplayMetrics();
        return String.valueOf(metrics.widthPixels) + "x" + metrics.heightPixels;
    }

    public static boolean isServiceRunning(Class<? extends Service> service, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo runningServiceInfo : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (service.getName().equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}