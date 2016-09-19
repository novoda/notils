package com.novoda.notils.orientation;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

/**
 * Will lock the orientation of your Activity depending on form factor & if in debug mode.
 * See {@link ScreenOrientationLockerFactory} for more details.
 */
public abstract class OrientationLockedActivity extends AppCompatActivity {

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        ScreenOrientationLockerFactory.newInstance(getResources())
                .create()
                .lockOnFormFactor(this);

        super.onCreate(savedInstanceState);
    }
}
