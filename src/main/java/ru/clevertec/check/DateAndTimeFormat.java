package main.java.ru.clevertec.check;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DateAndTimeFormat {

    private final Map<String, String> dateFormats;
    private final Map<String, String> timeFormats;

    DateAndTimeFormat() {
        LocalDateTime localDateTime = LocalDateTime.now();
        dateFormats = new HashMap<>();
        timeFormats = new HashMap<>();
        dateFormats.put("european_format", DateTimeFormatter.ofPattern("dd.MM.yyyy").format(localDateTime));
        timeFormats.put("european_format", DateTimeFormatter.ofPattern("HH:mm:ss").format(localDateTime));
    }

    public Map<String, String> getDateFormats() {
        return dateFormats;
    }

    public Map<String, String> getTimeFormats() {
        return timeFormats;
    }
}
