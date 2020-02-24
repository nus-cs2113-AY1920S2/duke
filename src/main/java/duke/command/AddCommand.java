package duke.command;

import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.Ui;

public class AddCommand extends Command {
    String command;
    public static final int TODO = 5;
    public static final int DEADLINE = 10;
    public static final int EVENT = 6;
    public static final int DATE = 3;
    public static final String BORDER = "____________________________________________________________\n";

    public AddCommand(String details, String command) {
        super(details);
        this.command = command;
    }

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
                String[] deadlineWords = this.details.split("/");
                String deadlineDescription = deadlineWords[0];
                String by = deadlineWords[1].substring(DATE);
                task = new Deadline(deadlineDescription, by);
                tasks.add(task);
                break;
            case "event":
                String[] eventWords = this.details.split("/");
                String eventDescription = eventWords[0];
                String at = eventWords[1].substring(DATE);
                task = new Event(eventDescription, at);
                tasks.add(task);
                break;
            }
            Ui.printAcknowledgement(tasks, tasks.getSize());

        } catch (NullPointerException e) {
            System.out.println(BORDER + "☹ OH NO!!! There is no such task to be done! :o(\n" + BORDER);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(BORDER + "☹ OH NO!!! The description of a " + this.command + " cannot be empty! :o(\n" + BORDER);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(BORDER + "☹ OH NO!!! The description of a " + this.command + " cannot be empty! :o(\n" + BORDER);
        }
    }
}
