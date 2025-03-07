package sonic.datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Formats date and time strings.
 * It provides methods for formatting dates, times, and datetime inputs.
 */
public class DateTime {

    /**
     * Formats a date string into a more readable format (e.g., "MMM dd yyyy").
     * If the input cannot be parsed, it returns the original string.
     *
     * @param dateString The date string to be formatted (e.g., "2025-03-05").
     * @throws DateTimeParseException If the input date string is not in the expected format.
     * @return The formatted date string (e.g., "Mar 05 2025") or the original string if invalid.
     */
    public static String formatDate(String dateString) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateString + "T00:00");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return dateTime.format(formatter);
        } catch (DateTimeParseException e) {
            return dateString;
        }
    }

    /**
     * Formats a time string into a more user-friendly format (e.g., "hh:mm am/pm").
     * If the input is invalid, it returns the original string.
     *
     * @param timeString The time string to be formatted (e.g., "1530").
     * @throws NumberFormatException If the input time string cannot be parsed.
     * @return The formatted time string (e.g., "3:30 pm") or the original string if invalid.
     */
    public static String formatTime(String timeString) {
        try {
            int hour = Integer.parseInt(timeString.substring(0, 2));
            int minute = Integer.parseInt(timeString.substring(2, 4));
            String period = (hour < 12) ? "am" : "pm";
            hour = (hour > 12) ? hour - 12 : hour;
            if (hour == 0) hour = 12;
            return hour + (minute == 0 ? "" : ":" + minute) + period;
        } catch (NumberFormatException e) {
            return timeString;
        }
    }

    /**
     * Splits a datetime string into date and time parts.
     *
     * @param dateTimeString The input datetime string.
     * @return A string array which divides the user input into parts, or null if invalid.
     */
    private static String[] splitDateTime(String dateTimeString) {
        if (dateTimeString == null || !dateTimeString.contains(" ")) {
            return null;
        }
        String[] parts = dateTimeString.split(" ");
        if (parts.length != 2) {
            return null;
        } else{
        return parts;
        }
    }

    /**
     * Determines the correct format for the given date and time components.
     *
     * @param part1 First component of the split datetime string.
     * @param part2 Second component of the split datetime string.
     * @return The formatted datetime string.
     */
    private static String determineFormat(String part1, String part2) {
        if (part1.matches("\\d{4}") && part2.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return formatTime(part1) + " " + formatDate(part2);
        } else if (part2.matches("\\d{4}") && part1.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return formatDate(part1) + " " + formatTime(part2);
        }
        return part1 + " " + part2;
    }

    /**
     * Formats a datetime string into a more user-friendly format.
     *
     * @param dateTimeString The datetime string to be formatted (e.g., "2025-03-05 1530").
     * @return The formatted datetime string (e.g., "Mar 05 2025 3:30 pm") or the original string if invalid.
     */
    public static String formatInputDateTime(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.isEmpty()) {
            return dateTimeString;
        }

        String[] parts = splitDateTime(dateTimeString);
        if (parts != null) {
            return determineFormat(parts[0], parts[1]);
        }

        if (dateTimeString.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return formatDate(dateTimeString);
        }

        if (dateTimeString.matches("\\d{4}")) {
            return formatTime(dateTimeString);
        }

        return dateTimeString;
    }
}
