package chatty.command;

public abstract class TaskCommand extends Command {
    private String description;

    public TaskCommand(String description) {
        super();
        this.description = description.trim();
    }

    public String getDescription() {
        return description;
    }
}
