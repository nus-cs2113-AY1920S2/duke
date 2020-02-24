package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Deletes a task from the task list based on the user specified index.
 */
public class DeleteCommand extends Command {
    public static final String BORDER = "____________________________________________________________\n";

    public DeleteCommand(String details) {
        super(details);
    }

    @Override
    public void executeCommand(TaskList tasks) {
        try {
            String deleteNumber = this.details;
            int delTaskNum = Integer.parseInt(deleteNumber);
            String delTaskName = tasks.get(delTaskNum -1).description;
            tasks.remove(delTaskNum - 1);
            Ui.deleteAcknowledgement(tasks, delTaskName);
        } catch (NullPointerException e) {
            System.out.println(BORDER + "☹ OH NO!!! There is no such task to delete! :o(\n" + BORDER);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(BORDER + "☹ OH NO!!! There is no such task to delete! :o(\n" + BORDER);
        }
        super.executeCommand(tasks);
    }
}
