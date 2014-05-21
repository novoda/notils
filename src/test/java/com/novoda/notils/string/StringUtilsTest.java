package com.novoda.notils.string;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class StringUtilsTest extends TestCase {

    public void testJoin() throws Exception {

        String string1 = "cool";
        String string2 = "story";
        String string3 = "bro";
        String delim   = ",";

        List<String> strings = new ArrayList<String>(3);
        strings.add(string1);
        strings.add(string2);
        strings.add(string3);

        String expected = string1+delim+string2+delim+string3;
        assertEquals("Strings get concatenated with delimiter", expected, StringUtils.join(strings, delim));


        string1 = "a";
        string2 = "story";
        string3 = "about";
        String string4 = "tests";
        delim   = "--";

        strings = new ArrayList<String>(3);
        strings.add(string1);
        strings.add(string2);
        strings.add(string3);
        strings.add(string4);

        expected = string1+delim+string2+delim+string3+delim+string4;
        assertEquals("Strings get concatenated with 2-char-delimiter", expected, StringUtils.join(strings, delim));

        strings = null;
        delim = ";";
        expected = "";
        assertEquals(expected, StringUtils.join(strings, delim));

        strings = new ArrayList<String>(0);
        delim = ";";
        expected = "";
        assertEquals("Joining collection=null returns empty string", expected, StringUtils.join(strings, delim));

        strings = new ArrayList<String>(0);
        delim   = ";";
        expected = "";
        assertEquals("Joining empty collection returns empty string", expected, StringUtils.join(strings, delim));

        string1 = "a";
        string2 = "story";
        string3 = "about";
        string4 = "missing delimiters";
        delim   = null;

        strings = new ArrayList<String>(3);
        strings.add(string1);
        strings.add(string2);
        strings.add(string3);
        strings.add(string4);

        expected = string1+string2+string3+string4;
        assertEquals("Delimiter=null is interpreted as empty string", expected, StringUtils.join(strings, delim));

        Integer integer1 = 1;
        Integer integer2 = 2;
        Integer integer3 = 42;
        Integer integer4 = 1337;
        delim = ";";
        List<Integer> integers = new ArrayList<Integer>(4);
        integers.add(integer1);
        integers.add(integer2);
        integers.add(integer3);
        integers.add(integer4);

        expected = integer1.toString()+delim+integer2.toString()+delim+integer3.toString()+delim+integer4.toString();
        assertEquals("Collection of Integer will be joined properly using the toString-method", expected, StringUtils.join(integers, delim));

    }
}