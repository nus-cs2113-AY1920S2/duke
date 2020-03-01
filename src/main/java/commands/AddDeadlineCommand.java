package commands;

import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends AddCommand {

    private String taskDescription;
    private LocalDateTime deadline;

    public static final String COMMAND_WORD = "deadline";

    public AddDeadlineCommand(String taskDescription, LocalDateTime deadline) {
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
