package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command{
    private static String findString;
    private static ArrayList<Integer> findCount;

    public FindCommand (String findString) {
        this.findString = findString;
        findCount = new ArrayList<>();
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        for(int i = 0; i<tasks.size(); i++) {
            if(tasks.getATask(i).getDescription().contains(findString)) {
                findCount.add(i);
            }
        }
        ui.showFindTask(tasks,findCount);
    }
}
