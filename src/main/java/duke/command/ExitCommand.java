package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command{

    public ExitCommand(String type) {
        super(type);
    }

    @Override
    public void execute(TaskList tasks){
        try {
            Storage.writeFile(tasks);
            Ui.exitMessage();
        }catch (IOException E){
            System.out.println("Something goes wrong.");
        }
    }
}
