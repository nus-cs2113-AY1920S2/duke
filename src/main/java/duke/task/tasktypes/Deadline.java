package duke.task.tasktypes;

import duke.utility.DateFormatter;
import java.time.DateTimeException;

/**
 * A class representing a deadline task.
 */
public class Deadline extends Task {

    /** Date to complete deadline by */
    private String by;
    private DateFormatter dateInfo;

    public Deadline (String description, String by) throws DateTimeException {
        super(description);

        dateInfo = new DateFormatter(by);

        this.by = by;
        this.taskType = TaskType.D;
    }

    public String getBy () {
        return by;
    }


    @Override
    public String toString() {

        if (dateInfo.hasValidDate() && dateInfo.hasValidTime()) {
            return String.format("[%s]%s (by: %s %s)", taskType, super.toString(), dateInfo.getDate(), dateInfo.getTime());

        } else if (dateInfo.hasValidTime()) {
            return String.format("[%s]%s (by: %s)", taskType, super.toString(), dateInfo.getTime());

        } else if (dateInfo.hasValidDate()) {
            return String.format("[%s]%s (by: %s)", taskType, super.toString(), dateInfo.getDate());

        }

        return "";
    }

}
