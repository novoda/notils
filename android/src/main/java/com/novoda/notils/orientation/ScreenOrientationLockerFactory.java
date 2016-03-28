package com.novoda.notils.orientation;

import android.content.res.Resources;

import com.novoda.android.BuildConfig;
import com.novoda.android.R;

/**
 * This factory can be used to instantiate a {@link ScreenOrientationLocker}
 * alternatively the {@link OrientationLockedActivity} can be extended for the same behaviour
 */
public class ScreenOrientationLockerFactory {

    private final boolean isDebug;
    private final boolean isTablet;

    public static ScreenOrientationLockerFactory newInstance(Resources resources) {
        boolean isDebug = BuildConfig.DEBUG;
        boolean isTablet = resources.getBoolean(R.bool.is_tablet);

        return new ScreenOrientationLockerFactory(isDebug, isTablet);
    }

    ScreenOrientationLockerFactory(boolean isDebug, boolean isTablet) {
        this.isDebug = isDebug;
        this.isTablet = isTablet;
    }

    /**
     * @return a {@link ScreenOrientationLocker} that will
     * lock a phone to portrait when not debugging and lock a tablet to landscape
     */
    public ScreenOrientationLocker create() {
        if (isDebug) {
            return new DebugScreenOrientationLocker();
        }

        if (isTablet) {
            return new TabletScreenOrientationLocker();
        } else {
            return new PhoneScreenOrientationLocker();
        }
    }
}
