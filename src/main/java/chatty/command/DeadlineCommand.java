package chatty.command;

public class DeadlineCommand extends TaskCommand {

    private String dateTime;

    public DeadlineCommand(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime.trim();
    }

    public String getDateTime() {
        return dateTime;
    }
}
