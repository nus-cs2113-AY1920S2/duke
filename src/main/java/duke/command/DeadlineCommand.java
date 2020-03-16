package duke.command;

import duke.storage.Storage;
import duke.data.TaskList;
import duke.ui.Ui;
import duke.task.Deadline;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The DeadlineCommand class is the Object that adds Deadline Object into the TaskList.
 * DeadlineCommand implement Command interface.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class DeadlineCommand implements Command {
    private String description;
    private LocalDate date;
    private LocalTime time;

    /**
     * Public constructor for DeadlineCommand.
     * @param description Description of the Task.
     */
    public DeadlineCommand(String description, LocalDate date, LocalTime time) {
        this.description = description;
        this.date = date;
        this.time = time;
    }

    /**
     * Executes the Deadline function and add the Deadline Task into the TaskList.
     * @param taskList Task Manager in charge of storing the Task required to be done.
     * @param ui Ui Object that deals with interaction with the user.
     * @param storage Storage Object that deals with the loading and Storing of Tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int taskCount = taskList.getTaskCount();
        taskList.addTask(new Deadline(description, date, time));
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + taskList.getTask(taskCount).toString());
        taskCount++;
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Boolean result indicate to the program if it should be exited.
     * @return False since command keyword does not match "bye".
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
