package sonic.ui;

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
     *
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
     * Formats a datetime string by splitting and formatting the date and time.
     *
     * @param dateTimeString The datetime string to be formatted (e.g., "2025-03-05 1530").
     * @return The formatted datetime string (e.g., "Mar 05 2025 3:30 pm") or the original string if invalid.
     */
    public static String formatInputDateTime(String dateTimeString) {
        if (dateTimeString == null || dateTimeString.isEmpty()) {
            return dateTimeString;
        }

        if (dateTimeString.contains(" ")) {
            String[] parts = dateTimeString.split(" ");
            if (parts.length == 2) {
                String time = parts[0];
                String date = parts[1];

                if (time.matches("\\d{4}") && date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    return formatTime(time) + " " + formatDate(date);
                }
                else if (date.matches("\\d{4}") && time.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    return formatDate(time) + " " + formatTime(date);
                }
            }
            return dateTimeString;
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
