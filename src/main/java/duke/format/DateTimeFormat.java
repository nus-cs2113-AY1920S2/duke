package duke.format;

import duke.exception.InvalidFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeFormat {

    private final static String[] ALL_DATE_FORMATS = {
            "dd/MM/yyyy", "d/MM/yyyy", "dd/M/yyyy", "d/M/yyyy", "dd/MM/yy", "d/MM/yy", "dd/M/yy", "d/M/yy",
            "dd-MM-yyyy", "d-MM-yyyy", "dd-M-yyyy", "d-M-yyyy", "dd-MM-yy", "d-MM-yy", "dd-M-yy", "d-M-yy"
    };
    private final static String[] ALL_TIME_FORMATS = {
            "hh:mma", "h:mma", "HH:mma", "HH:mm", "H:mm", "hh.mma", "h.mma", "HH.mma", "HH.mm", "H.mm",
            "HHmm", "Hmm", "Hmma", "hha", "ha", "HHa", "Ha", "H"
    };

    public static DateTime stringToDateTime(String string)
            throws InvalidDateTimeException, InvalidDateException, InvalidTimeException {
        String[] dateTimeData = string.split("\\s+");

        if (dateTimeData.length != 2) {
            throw new InvalidDateTimeException();
        }

        String date = dateTimeData[0].trim();
        String time = dateTimeData[1].trim().toUpperCase();

        return new DateTime(stringToDate(date), stringToTime(time));
    }

    public static LocalDate stringToDate(String date) throws InvalidDateException {
        if (date == null) {
            return LocalDate.now();
        }

        for (String format : ALL_DATE_FORMATS) {
            try {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {}
        }
        throw new InvalidDateException();
    }

    public static LocalTime stringToTime(String time) throws InvalidTimeException {
        if (time == null) {
            return null;
        }

        for (String format : ALL_TIME_FORMATS) {
            try {
                return LocalTime.parse(time, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {}
        }
        throw new InvalidTimeException();
    }

    public static class InvalidDateTimeException extends InvalidFormatException {}

    public static class InvalidDateException extends InvalidFormatException {}

    public static class InvalidTimeException extends InvalidFormatException {}
}
