package com.example.tournament.demo.Util;

public class CsvHelper {

    public static Long parseLongOrNull(String value) {
        if (value == null || value.equalsIgnoreCase("NULL")) {
            return null;
        }
        return Long.parseLong(value);
    }

    public static Integer parseIntOrNull(String value) {
        if (value == null || value.equalsIgnoreCase("NULL")) {
            return null;
        }
        return Integer.parseInt(value);
    }

}