package com.novoda.notils.orientation;

import android.app.Activity;
import android.content.pm.ActivityInfo;

class TabletScreenOrientationLocker implements ScreenOrientationLocker {

    @Override
    public void lockOnFormFactor(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
    }
}
