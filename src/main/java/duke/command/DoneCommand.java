package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to mark a task from the task list as completed, based on the user specified index.
 */
public class DoneCommand extends Command {
    public static final String BORDER = "____________________________________________________________\n";

    /**
     * Constructor to create a new done command.
     *
     * @param details the parameters of the command
     */
    public DoneCommand (String details) {
        super(details);
    }

    @Override
    public void executeCommand(TaskList tasks) {
        try {
            String doneNumber = this.details;
            int doneTaskNum = Integer.parseInt(doneNumber);
            tasks.get(doneTaskNum - 1).markAsDone();
            Ui.doneAcknowledgement(tasks, doneTaskNum);
        } catch (NullPointerException e) {
            System.out.println(BORDER + "☹ OH NO!!! There is no such task to be done! :o(\n" + BORDER);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(BORDER + "☹ OH NO!!! There is no such task to be done! :o(\n" + BORDER);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(BORDER + "☹ OH NO!!! There is no such task to be done! :o(\n" + BORDER);
        } catch (NumberFormatException e) {
            System.out.println(BORDER + "☹ OH NO!!! This is not a valid task number! :o(\n" + BORDER);
        }
        super.executeCommand(tasks);
    }
}
