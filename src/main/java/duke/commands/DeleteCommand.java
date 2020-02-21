package duke.commands;

import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(String parameters) {
        super(parameters);
    }

    @Override
    public void Execute(TaskList tasks){
        try {
            index = Integer.parseInt(this.parameters);
            System.out.println("  You have deleted: " + tasks.get(index - 1).getDescription() + "\n");
            tasks.remove(index - 1);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("This task does not exist!");
        } catch (NumberFormatException e) {
            System.out.println("Please specify a task number!");
        }
        super.Execute(tasks);
    }
}
