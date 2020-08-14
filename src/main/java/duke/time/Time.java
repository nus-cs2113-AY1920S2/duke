package duke.time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Time {
    public String dateFormat(String timeString) {
        try {
            LocalDate d1 = LocalDate.parse(timeString);
            return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            return timeString;
        }
    }
}
