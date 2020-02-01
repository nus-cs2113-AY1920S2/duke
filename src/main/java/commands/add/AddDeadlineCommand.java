package commands.add;

import commands.CommandResult;
import common.Month;
import data.task.DeadlineTask;
import data.task.Task;

public class AddDeadlineCommand extends AddCommand {

    public static final String COMMAND_WORD = "deadline";
    public static final char COMMAND_TYPE = 'D';
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a Deadline task to the DUKE system.\n"
            + "      Example: " + COMMAND_WORD+ " read a book";

    private final DeadlineTask deadlineTask;

    public AddDeadlineCommand(Task deadlineTask) {
        this.deadlineTask = (DeadlineTask) deadlineTask;
    }

    @Override
    public CommandResult execute() {
        duke.addTask(deadlineTask);
        //according to the data format
        if (deadlineTask.getTaskDeadlineDate()!=null){
            String timeString = String.format("%s %s %s",
                    Month.valueOf(deadlineTask.getTaskDeadlineDate().getMonthValue()),
                    deadlineTask.getTaskDeadlineDate().getDayOfMonth(),
                    deadlineTask.getTaskDeadlineDate().getYear());
            return new CommandResult(String.format(
                    MESSAGE_DEADLINE_SUCCESS,
                    COMMAND_TYPE,
                    deadlineTask.getChar(),
                    deadlineTask.getTaskDescription(),
                    timeString));
        } else {
            return new CommandResult(String.format(
                    MESSAGE_DEADLINE_SUCCESS,
                    COMMAND_TYPE,
                    deadlineTask.getChar(),
                    deadlineTask.getTaskDescription(),
                    deadlineTask.getTaskDeadline()));
        }
    }
}
