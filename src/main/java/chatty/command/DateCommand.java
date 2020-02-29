package chatty.command;

import java.time.LocalDate;

public class DateCommand extends Command {

    private LocalDate dateTime;

    public DateCommand(LocalDate dateTime) {
        super();
        this.dateTime = dateTime;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }
}
