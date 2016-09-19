package com.novoda.notils.orientation;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ScreenOrientationLockerTest {

    @Mock
    private Resources mockResources;
    @Mock
    private Activity mockActivity;

    @Before
    public void setUp() {
        initMocks(this);
        when(mockActivity.getRequestedOrientation()).thenReturn(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    @Test
    public void givenDebugBuildAndTablet_WhenLock_ThenLockDoesNotHappen() {
        boolean isDebug = true;
        boolean isTablet = true;

        ScreenOrientationLocker screenOrientationLocker = new ScreenOrientationLockerFactory(isDebug, isTablet).create();
        screenOrientationLocker.lockOnFormFactor(mockActivity);

        verify(mockActivity, never()).setRequestedOrientation(anyInt());
    }

    @Test
    public void givenDebugBuildAndPhone_WhenLock_ThenLockDoesNotHappen() {
        boolean isDebug = true;
        boolean isTablet = false;

        ScreenOrientationLocker screenOrientationLocker = new ScreenOrientationLockerFactory(isDebug, isTablet).create();
        screenOrientationLocker.lockOnFormFactor(mockActivity);

        verify(mockActivity, never()).setRequestedOrientation(anyInt());
    }

    @Test
    public void givenNoDebugBuildAndPhone_WhenLock_ThenScreenIsLockedToPortrait() {
        boolean isDebug = false;
        boolean isTablet = false;

        ScreenOrientationLocker screenOrientationLocker = new ScreenOrientationLockerFactory(isDebug, isTablet).create();
        screenOrientationLocker.lockOnFormFactor(mockActivity);

        verify(mockActivity).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
    }

    @Test
    public void givenNoDebugBuildAndPhoneAndAlreadyLockedToLandscape_WhenLock_ThenScreenIsNotLockedToPortrait() {
        boolean isDebug = false;
        boolean isTablet = false;
        when(mockActivity.getRequestedOrientation()).thenReturn(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ScreenOrientationLocker screenOrientationLocker = new ScreenOrientationLockerFactory(isDebug, isTablet).create();
        screenOrientationLocker.lockOnFormFactor(mockActivity);

        verify(mockActivity, never()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
    }

    @Test
    public void givenNoDebugBuildAndTablet_WhenLock_ThenScreenIsLockedToLandscape() {
        boolean isDebug = false;
        boolean isTablet = true;

        ScreenOrientationLocker screenOrientationLocker = new ScreenOrientationLockerFactory(isDebug, isTablet).create();
        screenOrientationLocker.lockOnFormFactor(mockActivity);

        verify(mockActivity).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
    }
}
