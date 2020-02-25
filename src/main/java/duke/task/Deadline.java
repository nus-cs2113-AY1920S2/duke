package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    // Stores information about deadline of task
    private String deadlineTime;
    private LocalDate deadlineDate;

    // Overloaded Constructor
    public Deadline(String descriptionWithDeadline) {
        super(getDescription(descriptionWithDeadline));
        taskType = 'D';
        String dateWithTime = getDeadline(descriptionWithDeadline);
        String[] splitDeadline = dateWithTime.split(" ",2);
        this.deadlineDate = LocalDate.parse(splitDeadline[0]);
        this.deadlineTime = splitDeadline[1];
    }

    // Abstracts out the description from the user given input and returns it
    private static String getDescription(String descriptionWithDeadline) {
        String[] words = descriptionWithDeadline.split("/",2);
        return words[0];
    }

    // Abstracts out the deadline from the user given input and returns it
    private static String getDeadline(String descriptionWithDeadline) {
        String[] split = descriptionWithDeadline.split("/",2);
        return (split[1].split(" ",2))[1];
    }

    // Returns the deadline in required format
    public String getBy() {
        return "(by: " + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " +  deadlineTime + " Hrs )";
    }

    // Returns the deadline
    public String getDeadlineInInputFormat() {
        return deadlineDate + " " + deadlineTime;
    }

    // Returns the task's type and status along with it's description as a string
    @Override
    public String getStatusWithDescription() {
        return "[" + this.taskType + "]" + super.getStatusWithDescription() + getBy();
    }
}
