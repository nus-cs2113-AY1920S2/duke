package commands;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_DONE = "  Nice! I've marked this task as done: \n"+
            "  [✓] %s";

    private final int toDoneIndex;

    public DoneCommand(int toDoneIndex) {
        this.toDoneIndex = toDoneIndex-1;
    }

    @Override
    public CommandResult execute() {
        duke.doneTask(toDoneIndex);
        return new CommandResult(String.format(MESSAGE_DONE,
                duke.getTaskList().getInternalList().get(toDoneIndex).getTaskDescription()));
    }

}
