package br.com.alura.agenda.database.converters;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @TypeConverter
    public String paraString(LocalDateTime value) {
        if(value != null) {
            return formatter.format(value);
        }
        return null;
    }

    @TypeConverter
    public LocalDateTime paraLocalDateTime(String value) {
        if(value!=null) {
            return LocalDateTime.parse(value, formatter);
        }
        return LocalDateTime.now();

    }
}
