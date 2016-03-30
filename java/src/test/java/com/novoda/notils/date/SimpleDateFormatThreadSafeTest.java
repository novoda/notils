package com.novoda.notils.date;

import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

public class SimpleDateFormatThreadSafeTest {
    String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    @Test
    public void givenStringDateInISOFormat_WhenTimeZoneIsUTC_ThenUTCTimeIsTheSame() {
        SimpleDateFormatThreadSafe formatter = givenFormatterForTimeZone(timeZoneUTC());
        
        Date date = parseTestDate("2016-01-01T10:00:00Z", formatter);

        assertEquals(date, createUTCDate(2016, Calendar.JANUARY, 1, 10));
    }

    @Test
    public void givenStringDateInISOFormat_WhenTimeZoneIsUTC_ThenUTCPlusOneTimeIsOneHourMore() {
        // This test exercises British Summer Time (device) vs UTC (API)
        SimpleDateFormatThreadSafe formatter = givenFormatterForTimeZone(timeZoneUTC());

        Date date = parseTestDate("2016-01-01T10:00:00Z", formatter);

        assertEquals(date, createDate(2016, Calendar.JANUARY, 1, 11, timeZoneUTCPlusOne()));
    }

    @Test
    public void givenStringDateInISOFormat_WhenTimeZoneIsUTCPlusOne_ThenUTCTimeIsOneHourLess() {
        SimpleDateFormatThreadSafe formatter = givenFormatterForTimeZone(timeZoneUTCPlusOne());
        
        Date date = parseTestDate("2016-01-01T10:00:00Z", formatter);

        assertEquals(date, createUTCDate(2016, Calendar.JANUARY, 1, 9));
    }

    @Test
    public void givenStringDateInISOFormat_WhenTimeZoneIsUTCPlusTwo_ThenUTCTimeIsTwoHoursLess() {
        SimpleDateFormatThreadSafe formatter = givenFormatterForTimeZone(timeZoneUTCPlusTwo());
        
        Date date = parseTestDate("2016-01-01T10:00:00Z", formatter);

        assertEquals(date, createUTCDate(2016, Calendar.JANUARY, 1, 8));
    }

    @Test
    public void givenStringDateInISOFormat_WhenTimeZoneIsUTCMinusOne_ThenUTCTimeIsOneHourMore() {
        SimpleDateFormatThreadSafe formatter = givenFormatterForTimeZone(timeZoneUTCMinusOne());
        
        Date date = parseTestDate("2016-01-01T10:00:00Z", formatter);

        assertEquals(date, createUTCDate(2016, Calendar.JANUARY, 1, 11));
    }

    @Test
    public void givenStringDateInISOFormat_WhenTimeZoneIsPST_ThenUTCTimeIsSevenHoursLess() {
        SimpleDateFormatThreadSafe formatter = givenFormatterForTimeZone(timeZonePST());

        Date date = parseTestDate("2016-01-01T10:00:00Z", formatter);

        assertEquals(date, createUTCDate(2016, Calendar.JANUARY, 1, 18));
    }

    private SimpleDateFormatThreadSafe givenFormatterForTimeZone(TimeZone timeZoneUTC) {
        return new SimpleDateFormatThreadSafe(DATE_PATTERN, timeZoneUTC);
    }

    private Date parseTestDate(String dateString, SimpleDateFormatThreadSafe formatter) {
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private Date createUTCDate(int year, int month, int date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, hour, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return calendar.getTime();
    }

    private Date createDate(int year, int month, int date, int hour, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date, hour, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.setTimeZone(timeZone);
        return calendar.getTime();
    }

    private TimeZone timeZoneUTC() {
        return TimeZone.getTimeZone("GMT");
    }

    private TimeZone timeZoneUTCPlusOne() {
        return TimeZone.getTimeZone("GMT+1");
    }

    private TimeZone timeZoneUTCPlusTwo() {
        return TimeZone.getTimeZone("GMT+2");
    }

    private TimeZone timeZoneUTCMinusOne() {
        return TimeZone.getTimeZone("GMT-1");
    }

    private TimeZone timeZonePST() {
        return TimeZone.getTimeZone("PST");
    }
}