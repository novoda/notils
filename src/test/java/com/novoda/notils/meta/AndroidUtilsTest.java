package com.novoda.notils.meta;

import android.util.DisplayMetrics;

import com.novoda.notils.java.AndroidUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AndroidUtilsTest {

    private static final int DENSITY = DisplayMetrics.DENSITY_DEFAULT;
    private static final int SIZE_IN_DIPS = 42;

    private static final int SIZE_IN_PIX = DENSITY * SIZE_IN_DIPS;

    @Test
    public void testConvertToPix() {
        int sizeInPix = AndroidUtils.convertToPix(DENSITY, SIZE_IN_DIPS);

        assertEquals(sizeInPix, SIZE_IN_PIX);
    }

}
