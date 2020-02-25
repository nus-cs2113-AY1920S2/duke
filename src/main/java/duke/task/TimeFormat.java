package duke.task;

import duke.enumerations.Day;
import duke.enumerations.Month;
import duke.parser.Parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represent TimeFormat object to parse Date
 */
public class TimeFormat {
    
    protected int START_INDEX;
    protected int END_INDEX;
    
    /**
     * Regex to check if yyyy-mm-dd or dd-mm-yyyy exist in the string
     * Regex also check if yyyy-MMM-dd or dd-MMM-yyyy exist in the string
     */
    private String timeRegex =
            "(\\d{4}[\\.\\-/](\\d{2}|\\w{3})[\\.\\-/]\\d{2})|(\\d{2}[\\.\\-/](\\d{2}|\\w{3})[\\" + ".\\-/]\\d{4})";
    
    protected String date = "";
    protected String day = "";
    protected Day theDay = null;
    protected LocalDate localDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private String patternDayMonthYear = "dd-MMM-yyyy";
    
    /**
     * Check if the valid date format is given, otherwise, check if user gives in day format,
     * and convert them to give the output of date and corresponding day stored in the variable
     * Giving in day format will calculate based on current timezone and give back LocalDate within the week
     *
     * @param by the date and time given by user
     * @return the rest of the string of date and time given by user
     */
    protected String checkDay(String by) {
        by = changeMonthToNumber(by);
        try {
            if (hasDateFormat(by)) {
                by = by.substring(END_INDEX);
                try {
                    localDate = LocalDate.parse(date, formatter);
                    localDate = LocalDate.parse(localDate.toString());
                } catch (DateTimeParseException e) {
                    localDate = LocalDate.parse(date);
                }
                date = localDate.format(DateTimeFormatter.ofPattern(patternDayMonthYear));
                day = Day.valueOf(localDate.getDayOfWeek().toString()).toString().toLowerCase();
            } else if (hasDayFormat(by)) {
                by = Parser.splitInputLine(by, Parser.SPILT_BY_SPACE)[1].trim();
                day = theDay.toString().toLowerCase();
                int currentDayValue = (LocalDate.now().getDayOfWeek()).getValue();
                int userDayValue = theDay.getDayNumber();
                if (userDayValue > currentDayValue) {
                    localDate = LocalDate.now().plusDays(userDayValue - currentDayValue);
                    date = localDate.format(DateTimeFormatter.ofPattern(patternDayMonthYear));
                } else if (userDayValue == currentDayValue) {
                    date = LocalDate.now().format(DateTimeFormatter.ofPattern(patternDayMonthYear));
                } else {
                    localDate = LocalDate.now().minusDays(currentDayValue - userDayValue);
                    date = localDate.format(DateTimeFormatter.ofPattern(patternDayMonthYear));
                }
            }
        } catch (DateTimeException | ArithmeticException e) {
            date = "[Invalid]";
            day = "[Invalid]";
        }
        return by.trim();
    }
    
    /**
     * Check if it has day format, Mon, Tue, ... , Sun within the first 3 letter of a string
     *
     * @param by the date and time given by user
     * @return true, if found within the first 3 letter, else return false
     */
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
    
    /**
     * Check if has date format, yyyy-mm--dd or dd-mm-yyyy
     *
     * @param by the date and time given by user
     * @return true if has date format, else return false
     */
    protected boolean hasDateFormat(String by) {
        return hasDateMatches(by, timeRegex);
    }
    
    /**
     * Loop through the pattern of regex to find match of the date format in the given string,
     * and gets the valid date format
     *
     * @param text  the date and time given by user
     * @param regex the quantifier to find the date format
     * @return true if found, else false
     */
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
    
    private String changeMonthToNumber(String input) {
        Month[] months = Month.values();
        for (Month month : months) {
            if (input.contains(month.toString())) {
                input = input.replace(month.toString(), month.getNumber());
                break;
            }
        }
        return input;
    }
}
