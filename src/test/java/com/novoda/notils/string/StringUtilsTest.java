package com.novoda.notils.string;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    private final static String THREE_DOTS = "…";
    public static final String RANDOM_STRING = "thisismystring";
    public static final int SHORT_STRING_LENGTH = 2;
    public static final int LONG_STRING_LENGTH = 30;

    @Test
    public void testJoinStringsWithComma() throws Exception {

        String string1 = "cool";
        String string2 = "story";
        String string3 = "bro";
        String delim = ",";

        List<String> strings = new ArrayList<String>(3);
        strings.add(string1);
        strings.add(string2);
        strings.add(string3);

        String expected = string1 + delim + string2 + delim + string3;
        assertEquals("Strings get concatenated with delimiter", expected, StringUtils.join(strings, delim));
    }

    @Test
    public void testJoinStringsWith2CharDelimiter() throws Exception {

        String string1 = "a";
        String string2 = "story";
        String string3 = "about";
        String string4 = "tests";
        String delim = "--";

        ArrayList<String> strings = new ArrayList<String>(3);
        strings.add(string1);
        strings.add(string2);
        strings.add(string3);
        strings.add(string4);

        String expected = string1 + delim + string2 + delim + string3 + delim + string4;
        assertEquals("Strings get concatenated with 2-char-delimiter", expected, StringUtils.join(strings, delim));

    }

    @Test
    public void testJoinWithNullCollection() throws Exception {

        Collection<?> strings = null;
        String delim = ";";

        String expected = "";
        assertEquals(expected, StringUtils.join(strings, delim));
    }

    @Test
    public void testJoinEmptyCollection() throws Exception {

        ArrayList<String> strings = new ArrayList<String>(0);
        String delim = ";";

        String expected = "";
        assertEquals("Joining empty collection returns empty string", expected, StringUtils.join(strings, delim));
    }

    @Test
    public void testJoinStringsWithNullDelimiter() throws Exception {

        String string1 = "a";
        String string2 = "story";
        String string3 = "about";
        String string4 = "missing delimiters";
        String delim = null;

        ArrayList<String> strings = new ArrayList<String>(3);
        strings.add(string1);
        strings.add(string2);
        strings.add(string3);
        strings.add(string4);

        String expected = string1 + string2 + string3 + string4;
        assertEquals("Delimiter=null is interpreted as empty string", expected, StringUtils.join(strings, delim));
    }

    @Test
    public void testJoinIntegersWithSemicolon() throws Exception {

        Integer integer1 = 1;
        Integer integer2 = 2;
        Integer integer3 = 42;
        Integer integer4 = 1337;
        String delim = ";";

        List<Integer> integers = new ArrayList<Integer>(4);
        integers.add(integer1);
        integers.add(integer2);
        integers.add(integer3);
        integers.add(integer4);

        String expected = integer1.toString() + delim + integer2.toString() + delim + integer3.toString() + delim + integer4.toString();
        assertEquals("Collection of Integer will be joined properly using the toString-method", expected, StringUtils.join(integers, delim));
    }

    @Test
    public void testLongStringLengthIsCropAtMaximum() throws Exception {

        String string = StringUtils.truncateIfLengthMoreThan(SHORT_STRING_LENGTH, RANDOM_STRING);

        assertEquals(string.length(), SHORT_STRING_LENGTH);
    }

    @Test
    public void testStringLengthUnchangedIfStringLengthIsLessThanMaximum() throws Exception {

        String croppedString = StringUtils.truncateIfLengthMoreThan(LONG_STRING_LENGTH, RANDOM_STRING);

        assertEquals(croppedString.length(), RANDOM_STRING.length());
    }

    @Test
    public void testLongStringHasThreeDotsWhenLenghtIsMoreThanMaximum() throws Exception {

        String croppedString = StringUtils.truncateIfLengthMoreThan(SHORT_STRING_LENGTH, RANDOM_STRING);

        assertEquals(String.valueOf(croppedString.charAt(SHORT_STRING_LENGTH - 1)), THREE_DOTS);
    }

    @Test
    public void testStringIsUntouchedWhenLengthIsLessThanMaximum() throws Exception {

        String croppedString = StringUtils.truncateIfLengthMoreThan(LONG_STRING_LENGTH, RANDOM_STRING);

        assertEquals(RANDOM_STRING, croppedString);
    }
}
