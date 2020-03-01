package commands;

import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.Ui;

import java.util.function.DoubleUnaryOperator;

public class AddDeadlineCommand extends AddCommand {

    private String taskDescription;
    private String deadline;

    public static final String COMMAND_WORD = "deadline";

    public AddDeadlineCommand(String taskDescription, String deadline) {
        this.taskDescription = taskDescription;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline deadlineTask = new Deadline(this.taskDescription, this.deadline);
        tasks.addTask(deadlineTask);
        storage.saveTasks(tasks);
        ui.printNewTask(tasks);
    }
}
