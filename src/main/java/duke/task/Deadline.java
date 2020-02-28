package duke.task;

import duke.command.AddCommand;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description,String by) {
        super(description);
        this.by=LocalDate.parse(by);
    }

    public Deadline(AddCommand addCommand){
        super(addCommand);
        this.by = LocalDate.parse(addCommand.getTimeNotes());
    }

    @Override
    public String toString(){
        String taskType="[D]";
        String detail = taskType + " [" + getStatusIcon() + "] " + description +
                " (by: "+by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+" )";
        return detail;
    }
}
