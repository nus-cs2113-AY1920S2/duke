package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The EventCommand class is the Object that adds Event Object into the TaskList.
 * EventCommand implement Command interface.
 * @author Lam Yue Wei
 * @version CS2113 AY19/20 Sem 2 Duke
 */
public class EventCommand implements Command {
    private String description;
    private LocalDate date;
    private LocalTime time;

    /**
     * Public constructor for EventCommand.
     * @param description Description of the Task.
     */
    public EventCommand(String description, LocalDate date, LocalTime time) {
        this.description = description;
        this.date = date;
        this.time = time;
    }

    /**
     * Executes the event function and add the Event Task into the TaskList.
     * @param taskList Task Manager in charge of storing the Task required to be done.
     * @param ui Ui Object that deals with interaction with the user.
     * @param storage Storage Object that deals with the loading and Storing of Tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int taskCount = taskList.getTaskCount();
        taskList.addTask(new Event(description, date, time));
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
