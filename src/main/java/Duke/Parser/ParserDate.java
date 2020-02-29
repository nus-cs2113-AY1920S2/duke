package Duke.Parser;

import Duke.Exception.DukeDateParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ParserDate {
    public static String parseStringToDate(String date) throws DukeDateParseException {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(date, formatter);
            return formatter.format(localDate);
        } catch (DateTimeParseException e) {
            throw new DukeDateParseException();
        }
    }
}
