package duke.task;

import duke.enumerations.Day;
import duke.parser.Parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeFormat {
    
    protected int START_INDEX;
    protected int END_INDEX;
    private String timeRegex = "(\\d{4}[\\.\\-/]\\d{2}[\\.\\-/]\\d{2})|(\\d{2}[\\.\\-/]\\d{2}[\\.\\-/]\\d{4})";
    protected String date = "";
    protected String day = "";
    protected Day theDay = null;
    protected LocalDate localDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    protected String checkDay(String by) {
        try {
            if (hasDateFormat(by)) {
                by = by.substring(END_INDEX);
                try {
                    localDate = LocalDate.parse(date, formatter);
                    localDate = LocalDate.parse(localDate.toString());
                } catch (DateTimeParseException e) {
                    localDate = LocalDate.parse(date);
                }
                date = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                day = Day.valueOf(localDate.getDayOfWeek().toString()).toString().toLowerCase();
            } else if (hasDayFormat(by)) {
                by = Parser.splitInputLine(by, Parser.SPILT_BY_SPACE)[1].trim();
                day = theDay.toString().toLowerCase();
                int currentDayValue = (LocalDate.now().getDayOfWeek()).getValue();
                int userDayValue = theDay.getDayNumber();
                if (userDayValue > currentDayValue) {
                    localDate = LocalDate.now().plusDays(userDayValue - currentDayValue);
                    date = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                } else if (userDayValue == currentDayValue) {
                    date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                } else {
                    localDate = LocalDate.now().minusDays(currentDayValue - userDayValue);
                    date = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                }
            }
        } catch (DateTimeException | ArithmeticException e) {
            //System.out.println(e);
            date = "[Invalid Date]";
            day = "[Invalid Day]";
        }
        return by.trim();
    }
    
    protected boolean hasDayFormat(String by) {
        Day[] days = Day.values();
        for (Day day : days) {
            if (day.toString().toLowerCase().contains(by.substring(0, 3).toLowerCase())) {
                theDay = day;
                return true;
            }
        }
        return false;
    }
    
    protected boolean hasDateFormat(String by) {
        return hasDateMatches(by, timeRegex);
    }
    
    //Solution below adapted from
    //https://stackoverflow.com/questions/8938498/get-the-index-of-a-pattern-in-a-string-using-regex
    protected boolean hasDateMatches(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            date = matcher.group();
            if (date != null) {
                START_INDEX = matcher.start();
                END_INDEX = matcher.end();
                return true;
            }
        }
        return false;
    }
}
