package duke.format;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static duke.format.DateTimeFormat.stringToDate;
import static duke.format.DateTimeFormat.stringToTime;

public class DateTime {
    public static final String FORMAT = "<date dd/mm/yyyy> <time hh:mm>";
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String TIME_FORMAT = "hh:mma";

    private LocalDate date;
    private LocalTime time;

    public DateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    public void setDate(String date) throws DateTimeFormat.InvalidDateException {
        this.date = stringToDate(date);
    }

    public void setTime(String time) throws DateTimeFormat.InvalidTimeException {
        this.time = stringToTime(time);
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    private boolean hasTime() {
        return time != null;
    }

    private boolean isToday() {
        return this.date.isEqual(LocalDate.now());
    }

    private boolean isTomorrow() {
        return this.date.minusDays(1).isEqual(LocalDate.now());
    }

    private boolean isDue() {
        return this.date.isBefore(LocalDate.now()) ||
                (hasTime() && isToday() && this.time.isBefore(LocalTime.now()));
    }

    private String dateToString() {
        if (isToday()) {
            return "today";
        } else if (isTomorrow()) {
            return "tomorrow";
        } else {
            return getDate();
        }
    }

    private String timeToString() {
        return hasTime() ? getTime() : "";
    }

    @Override
    public String toString() {
        String toShow = dateToString() + " " + timeToString();
        return (isDue()) ? toShow + " [OVERDUE!!]" : toShow;
    }
}
