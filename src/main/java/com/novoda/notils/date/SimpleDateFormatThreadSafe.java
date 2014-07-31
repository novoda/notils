package com.novoda.notils.date;

import java.text.AttributedCharacterIterator;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SimpleDateFormatThreadSafe extends SimpleDateFormat {

    private final ThreadLocal<SimpleDateFormat> localSimpleDateFormat;

    /**
     * @see java.text.SimpleDateFormat#SimpleDateFormat(String, java.text.DateFormatSymbols)
     */
    public SimpleDateFormatThreadSafe(final String pattern, final DateFormatSymbols formatSymbols) {
        super(pattern, formatSymbols);
        localSimpleDateFormat = new ThreadLocal<SimpleDateFormat>() {
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat(pattern, formatSymbols);
            }
        };
    }

    /**
     * @see java.text.SimpleDateFormat#SimpleDateFormat(String, java.util.Locale)
     */
    public SimpleDateFormatThreadSafe(final String pattern, final Locale locale) {
        super(pattern, locale);
        localSimpleDateFormat = new ThreadLocal<SimpleDateFormat>() {
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat(pattern, locale);
            }
        };
    }

    @Override
    public Object parseObject(String source) throws ParseException {
        return localSimpleDateFormat.get().parseObject(source);
    }

    @Override
    public Date parse(String source) throws ParseException {
        return localSimpleDateFormat.get().parse(source);
    }

    @Override
    public Object parseObject(String source, ParsePosition position) {
        return localSimpleDateFormat.get().parseObject(source, position);
    }

    @Override
    public void setCalendar(Calendar newCalendar) {
        localSimpleDateFormat.get().setCalendar(newCalendar);
    }

    @Override
    public Calendar getCalendar() {
        return localSimpleDateFormat.get().getCalendar();
    }

    @Override
    public void setNumberFormat(NumberFormat newNumberFormat) {
        localSimpleDateFormat.get().setNumberFormat(newNumberFormat);
    }

    @Override
    public NumberFormat getNumberFormat() {
        return localSimpleDateFormat.get().getNumberFormat();
    }

    @Override
    public void setTimeZone(TimeZone zone) {
        localSimpleDateFormat.get().setTimeZone(zone);
    }

    @Override
    public TimeZone getTimeZone() {
        return localSimpleDateFormat.get().getTimeZone();
    }

    @Override
    public void setLenient(boolean lenient) {
        localSimpleDateFormat.get().setLenient(lenient);
    }

    @Override
    public boolean isLenient() {
        return localSimpleDateFormat.get().isLenient();
    }

    @Override
    public void set2DigitYearStart(Date startDate) {
        localSimpleDateFormat.get().set2DigitYearStart(startDate);
    }

    @Override
    public Date get2DigitYearStart() {
        return localSimpleDateFormat.get().get2DigitYearStart();
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition position) {
        return localSimpleDateFormat.get().format(date, toAppendTo, position);
    }

    @Override
    public AttributedCharacterIterator formatToCharacterIterator(Object object) {
        return localSimpleDateFormat.get().formatToCharacterIterator(object);
    }

    @Override
    public Date parse(String text, ParsePosition position) {
        return localSimpleDateFormat.get().parse(text, position);
    }

    @Override
    public String toPattern() {
        return localSimpleDateFormat.get().toPattern();
    }

    @Override
    public String toLocalizedPattern() {
        return localSimpleDateFormat.get().toLocalizedPattern();
    }

    @Override
    public void applyPattern(String pattern) {
        localSimpleDateFormat.get().applyPattern(pattern);
    }

    @Override
    public void applyLocalizedPattern(String pattern) {
        localSimpleDateFormat.get().applyLocalizedPattern(pattern);
    }

    @Override
    public DateFormatSymbols getDateFormatSymbols() {
        return localSimpleDateFormat.get().getDateFormatSymbols();
    }

    @Override
    public void setDateFormatSymbols(DateFormatSymbols newFormatSymbols) {
        localSimpleDateFormat.get().setDateFormatSymbols(newFormatSymbols);
    }

    @Override
    public String toString() {
        return localSimpleDateFormat.get().toString();
    }

    @SuppressWarnings("CloneDoesntCallSuperClone") // it calls our wrapped clone
    @Override
    public Object clone() {
        return localSimpleDateFormat.get().clone();
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass") // it is done in the super
    @Override
    public boolean equals(Object object) {
        return localSimpleDateFormat.get().equals(object);
    }

    @Override
    public int hashCode() {
        return localSimpleDateFormat.get().hashCode();
    }

}
