package com.novoda.notils.java.caster;

import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class ClassesTest {

    @Test
    public void testCastingIsWorking() {
        Object object = new Integer(0);

        Integer integer = Classes.from(object);

        assertThat(integer, instanceOf(Integer.class));
    }

    @Test(expected = ClassCastException.class)
    public void testThatAWrongCastingThrowsAnException() {
        Object object = new Object();

        Integer integer = Classes.from(object);
    }
}
