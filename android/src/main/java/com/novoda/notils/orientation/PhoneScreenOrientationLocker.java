package com.novoda.notils.orientation;

import android.app.Activity;
import android.content.pm.ActivityInfo;

class PhoneScreenOrientationLocker implements ScreenOrientationLocker {

    @Override
    public void lockOnFormFactor(Activity activity) {
        if (orientationNotAlreadySpecified(activity)) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }
    }

    private boolean orientationNotAlreadySpecified(Activity activity) {
        return activity.getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
    }
}
