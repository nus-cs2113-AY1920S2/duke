package duke.commands;

import duke.Util.Tasklist;

import java.io.FileNotFoundException;

public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public void execute() throws FileNotFoundException {
        Tasklist tasklist = new Tasklist();
        tasklist.showList();
    }
}
