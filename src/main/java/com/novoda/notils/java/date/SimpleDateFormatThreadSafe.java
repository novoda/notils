package com.novoda.notils.java.date;

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

public class SimpleDateFormatThreadSafe {

    private final ThreadLocal<SimpleDateFormat> localSimpleDateFormat;

    /**
     * @see java.text.SimpleDateFormat#SimpleDateFormat(String, java.text.DateFormatSymbols)
     */
    public SimpleDateFormatThreadSafe(final String pattern, final DateFormatSymbols formatSymbols) {
        this(new SimpleDateFormat(pattern, formatSymbols));
    }

    /**
     * @see java.text.SimpleDateFormat#SimpleDateFormat(String, java.util.Locale)
     */
    public SimpleDateFormatThreadSafe(final String pattern, final Locale locale) {
        this(new SimpleDateFormat(pattern, locale));
    }

    public SimpleDateFormatThreadSafe(final SimpleDateFormat localSimpleDateFormat) {
        this.localSimpleDateFormat = new ThreadLocal<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat initialValue() {
                return localSimpleDateFormat;
            }
        };
    }

    public Object parseObject(String source) throws ParseException {
        return localSimpleDateFormat.get().parseObject(source);
    }

    public Date parse(String source) throws ParseException {
        return localSimpleDateFormat.get().parse(source);
    }

    public Object parseObject(String source, ParsePosition position) {
        return localSimpleDateFormat.get().parseObject(source, position);
    }

    public void setCalendar(Calendar newCalendar) {
        localSimpleDateFormat.get().setCalendar(newCalendar);
    }

    public Calendar getCalendar() {
        return localSimpleDateFormat.get().getCalendar();
    }

    public void setNumberFormat(NumberFormat newNumberFormat) {
        localSimpleDateFormat.get().setNumberFormat(newNumberFormat);
    }

    public NumberFormat getNumberFormat() {
        return localSimpleDateFormat.get().getNumberFormat();
    }

    public void setTimeZone(TimeZone zone) {
        localSimpleDateFormat.get().setTimeZone(zone);
    }

    public TimeZone getTimeZone() {
        return localSimpleDateFormat.get().getTimeZone();
    }

    public void setLenient(boolean lenient) {
        localSimpleDateFormat.get().setLenient(lenient);
    }

    public boolean isLenient() {
        return localSimpleDateFormat.get().isLenient();
    }

    public void set2DigitYearStart(Date startDate) {
        localSimpleDateFormat.get().set2DigitYearStart(startDate);
    }

    public Date get2DigitYearStart() {
        return localSimpleDateFormat.get().get2DigitYearStart();
    }

    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition position) {
        return localSimpleDateFormat.get().format(date, toAppendTo, position);
    }

    public String format(Date date) {
        return localSimpleDateFormat.get().format(date);
    }

    public AttributedCharacterIterator formatToCharacterIterator(Object object) {
        return localSimpleDateFormat.get().formatToCharacterIterator(object);
    }

    public Date parse(String text, ParsePosition position) {
        return localSimpleDateFormat.get().parse(text, position);
    }

    public String toPattern() {
        return localSimpleDateFormat.get().toPattern();
    }

    public String toLocalizedPattern() {
        return localSimpleDateFormat.get().toLocalizedPattern();
    }

    public void applyPattern(String pattern) {
        localSimpleDateFormat.get().applyPattern(pattern);
    }

    public void applyLocalizedPattern(String pattern) {
        localSimpleDateFormat.get().applyLocalizedPattern(pattern);
    }

    public DateFormatSymbols getDateFormatSymbols() {
        return localSimpleDateFormat.get().getDateFormatSymbols();
    }

    public void setDateFormatSymbols(DateFormatSymbols newFormatSymbols) {
        localSimpleDateFormat.get().setDateFormatSymbols(newFormatSymbols);
    }

    @SuppressWarnings("CloneDoesntCallSuperClone") // Wrapper object should delegate even clone
    @Override
    public Object clone() {
        return new SimpleDateFormatThreadSafe((SimpleDateFormat) localSimpleDateFormat.get().clone());
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass") // Wrapper object should delegate even equals
    @Override
    public boolean equals(Object object) {
        return localSimpleDateFormat.get().equals(object);
    }

    @Override
    public int hashCode() {
        return localSimpleDateFormat.get().hashCode();
    }

    @Override
    public String toString() {
        return localSimpleDateFormat.get().toString();
    }

}
