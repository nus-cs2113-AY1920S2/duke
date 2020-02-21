package duke.commands;

import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    int index;

    public DoneCommand(String parameters) {
        super(parameters);
    }

    @Override
    public void Execute(TaskList tasks){
        try {
            index = Integer.parseInt(this.parameters);
            if (!tasks.get(index - 1).getIsDone()) {
                tasks.get(index - 1).markAsDone();
                Ui.printDone(tasks.get(index - 1).getDescription());
            } else {
                System.out.println("You have already done this task!");
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("This task does not exist!");
        } catch (NumberFormatException e) {
            System.out.println("Please specify a task number!");
        }
        super.Execute(tasks);
    }
}
