package com.novoda.notils.string;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

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
        assertEquals(expected, StringUtils.join(strings, delim));


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
        assertEquals(expected, StringUtils.join(strings, delim));

        strings = new ArrayList<String>(0);
        delim   = ";";
        expected = "";
        assertEquals(expected, StringUtils.join(strings, delim));

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
        assertEquals(expected, StringUtils.join(integers, delim));

    }
}