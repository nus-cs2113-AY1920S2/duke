package chatty.command;

import java.time.LocalDate;

public class DeadlineCommand extends TaskCommand {

    private LocalDate dateTime;

    public DeadlineCommand(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }
}
