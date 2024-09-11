package com.example.tournament.demo.Util;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {

    public static void parseDate(String date) {

        List<DateTimeFormatter> formatters = new ArrayList<>();

        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 2024-09-12
        formatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // 12/09/2024
        formatters.add(DateTimeFormatter.ofPattern("MM-dd-yyyy")); // 09-12-2024
        formatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy")); // 12-09-2024
        formatters.add(DateTimeFormatter.ofPattern("MMMM d, yyyy")); // September 12, 2024
        formatters.add(DateTimeFormatter.ofPattern("d MMM yyyy")); // 12 Sep 2024
        formatters.add(DateTimeFormatter.ofPattern("EEE, MMM d, yyyy")); // Thu, Sep 12, 2024
        formatters.add(DateTimeFormatter.ofPattern("M/d/yyyy")); // 7/14/2024 (нов формат)

        for (DateTimeFormatter formatter : formatters) {
            try {
                return;
            } catch (Exception e) {

            }
        }

        throw new IllegalArgumentException("Invalid date format: " + date);
    }
}
