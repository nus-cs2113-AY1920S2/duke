package chatty.command;

import java.time.LocalDate;

public class EventCommand extends TaskCommand {

    private LocalDate startTime;
    private LocalDate endTime;

    public EventCommand(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }
}
