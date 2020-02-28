package duke.task;

import duke.command.AddCommand;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate period;

    public Event(String description,String period) {
        super(description);
        this.period=LocalDate.parse(period);
    }

    public Event(AddCommand addCommand){
        super(addCommand);
        this.period = LocalDate.parse(addCommand.getTimeNotes());
    }


    @Override
    public String toString(){
        String taskType="[E]";
        String detail = taskType + " [" + getStatusIcon() + "] " + description+
                " (at: "+period.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+")";
        return detail;
    }
}
