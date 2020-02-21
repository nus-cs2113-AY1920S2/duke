package duke.commands;

import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand(String parameters) {
        super(parameters);
    }

    @Override
    public void Execute(TaskList tasks){
        Ui.printList(tasks);
    }
}
