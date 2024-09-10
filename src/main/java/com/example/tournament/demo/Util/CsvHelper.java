package com.example.tournament.demo.Util;

public class CsvHelper {

    // Хелпър метод за Long стойности
    public static Long parseLongOrNull(String value) {
        if (value == null || value.equalsIgnoreCase("NULL")) {
            return null; // Връщаме null вместо 0
        }
        return Long.parseLong(value);
    }

    // Хелпър метод за Integer стойности
    public static Integer parseIntOrNull(String value) {
        if (value == null || value.equalsIgnoreCase("NULL")) {
            return null; // Връщаме null вместо 0
        }
        return Integer.parseInt(value);
    }
}
