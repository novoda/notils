package com.novoda.notils.string;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryUtilsTest {

    @Test
    public void testSizeOfZeroReturnsEmptyString() {

        String expected = "";
        assertEquals(expected, QueryUtils.createSelectionPlaceholdersOfSize(0));
    }

    @Test
    public void testSizeOfOneReturnsJustQuestionMark() {

        String expected = "?";
        assertEquals(expected, QueryUtils.createSelectionPlaceholdersOfSize(1));
    }

    @Test
    public void testSizeBiggerThanOneReturnsSameNumberOfQuestionMarkSeparatedByCommas() {

        String expected = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        assertEquals(expected, QueryUtils.createSelectionPlaceholdersOfSize(10));
    }

}
