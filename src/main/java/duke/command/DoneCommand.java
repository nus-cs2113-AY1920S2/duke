package duke.command;

import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    public static final String BORDER = "____________________________________________________________\n";

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
            System.out.println(BORDER + "☹ OH NO!!! There is no such task to delete! :o(\n" + BORDER);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(BORDER + "☹ OH NO!!! There is no such task to delete! :o(\n" + BORDER);
        }
        super.executeCommand(tasks);
    }
}
