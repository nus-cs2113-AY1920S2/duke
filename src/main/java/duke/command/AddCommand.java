package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.Ui;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    String command;
    public static final int TODO = 5;
    public static final int DEADLINE = 10;
    public static final int EVENT = 6;
    public static final int DATE = 3;
    public static final String BORDER = "____________________________________________________________\n";

    /**
     * Constructor to add a new command.
     *
     * @param details the parameters of the command
     * @param command the type of task to be added
     */
    public AddCommand(String details, String command) {
        super(details);
        this.command = command;
    }

    /**
     * Adds task to the task list based on task type.
     * @param tasks list of tasks.
     */
    @Override
    public void executeCommand(TaskList tasks) {
        Task task;
        try {
            switch (this.command) {
            case "todo":
                task = new Todo(this.details);
                tasks.add(task);
                break;
            case "deadline":
                String[] deadlineWords = this.details.split("/",2);
                String deadlineDescription = deadlineWords[0];
                String by = deadlineWords[1].substring(DATE);
                task = new Deadline(deadlineDescription, by);
                tasks.add(task);
                break;
            case "event":
                String[] eventWords = this.details.split("/",2);
                String eventDescription = eventWords[0];
                String at = eventWords[1].substring(DATE);
                task = new Event(eventDescription, at);
                tasks.add(task);
                break;
            }
            Ui.printAcknowledgement(tasks, tasks.getSize());
            super.executeCommand(tasks);

        }  catch (DateTimeParseException e) {
            System.out.println(BORDER + "☹ OH NO!!! The date format is: yyyy-mm-dd.\n" + BORDER);
        }  catch (StringIndexOutOfBoundsException | DukeException e) {
            System.out.println(BORDER + "☹ OH NO!!! The description/date of the command '" + this.command + "' cannot be empty! :o(\n" + BORDER);
        }  catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(BORDER + "☹ OH NO!!! The description/date of the command '" + this.command + "' cannot be empty! :o(\n" + BORDER);
        }
    }
}
