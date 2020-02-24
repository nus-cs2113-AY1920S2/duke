package duke.TaskList.task.commands;

import duke.TaskList.task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private char taskType;
    private LocalDateTime eventDateTime;
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter dateTimeFormat2 = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Event(String description, String date) {
        super(description);
        taskType = 'E';
        getDateTime(date);
    }

    public void getDateTime(String date) throws DateTimeParseException {
        String [] dateTimeSplit = date.split(" ");
        if (dateTimeSplit.length > 1){
            date = dateTimeSplit[0] + "T" + dateTimeSplit[1];
            eventDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
        } else {
            LocalDate eventDate = LocalDate.parse(date);
            eventDateTime = eventDate.atStartOfDay();
        }
    }

    public String toString() {
        return "[" + taskType + "][" + super.getTaskStatus() + "] " + super.getTaskDescription() +
                " (at: " + getDateTimeStringFormat2() + ")";
    }

    @Override
    public char getTaskType() {
        return this.taskType;
    }

    @Override
    public LocalDateTime getDateTime() {
        return eventDateTime;
    }

    @Override
    public String getDateTimeString(){
        return eventDateTime.format(dateTimeFormat);
    }

    @Override
    public String getDateTimeStringFormat2(){
        return eventDateTime.format(dateTimeFormat2);
    }

}
