package Duke.Commands;

import Duke.Exception.DukeException;
import Duke.Storage.Storage;
import Duke.Task.Task;
import Duke.Ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasks = storage.getTasks();
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: tasks) {
            if (task.toString().contains(keyword)) {
                result.add(task);
            }
        }
        Ui.displaySearchResults(result);
    }
}
