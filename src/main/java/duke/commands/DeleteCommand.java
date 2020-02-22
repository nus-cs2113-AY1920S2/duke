package duke.commands;

import duke.Util.Tasklist;
import duke.Util.UI;
import duke.taskmanager.Tasks;

import java.util.InputMismatchException;

public class DeleteCommand extends Command{
    protected int index;
    public UI ui;
    public DeleteCommand (UI ui)  {
        this.ui = ui;
    }

    public boolean isExist() {
        Tasklist.showList();
        int index = ui.getIntegerInput();
        ui.clearInput();
        return index < Tasklist.getSize();
    }

    @Override
    public void execute() {
        Tasks task = Tasklist.getTask(index);
        try {
            if (isExist()) {
                Tasklist.delete(index);
                System.out.println("    Task " + index + ": " +
                        task.getTask() + " has been deleted!\n" +
                        "    Input Any key to continue.");
                Tasklist.saveTaskList();
            } else {
                System.out.println("    Sorry, task does not exist ");
            }
        } catch (InputMismatchException e) {
            System.out.println("    Not a valid input.");
            ui.clearInput();
        }
    }
}
