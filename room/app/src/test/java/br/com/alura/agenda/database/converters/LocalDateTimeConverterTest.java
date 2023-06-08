package br.com.alura.agenda.database.converters;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;


public class LocalDateTimeConverterTest {

    @Test
    public void converteDataDeStringParaLocalDateTime() {
        LocalDateTime expected = LocalDateTime.of(1979, 7, 29, 11, 5, 0);
        LocalDateTime actual = new LocalDateTimeConverter().paraLocalDateTime("29/07/1979 11:05:00");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void converteDataNulaDeStringParaLocalDateTime() {
        LocalDateTime actual = new LocalDateTimeConverter().paraLocalDateTime(null);
        Assert.assertNotNull(actual);
    }

    @Test
    public void converteDataDeLocalDateTimeParaString() {
        String expected =  "29/07/1979 11:05:00";
        String actual = new LocalDateTimeConverter().paraString(LocalDateTime.of(1979, 7, 29, 11, 5, 0));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void converteDataNulaDeLocalDateTimeParaString() {
        String actual = new LocalDateTimeConverter().paraString(null);
        Assert.assertNull(actual);
    }

}