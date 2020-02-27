package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;
/** Gives users a way to find a task by searching for a keyword. */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public FindCommand(String fullCommand, String taskType, String args) {
        super(fullCommand, taskType, args);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        find(tasks, args, ui);
    }

    /**
     * Finds a task by searching for a keyword.
     * @param tasks task list
     * @param args the keyword
     * @param ui Duke's UI
     * @throws DukeException if user do not enter a keyword or find nothing.
     */
    public void find(TaskList tasks, String args, Ui ui) throws DukeException {
        boolean isEmptyKeyword = args.length()==0;
        if(isEmptyKeyword){
            throw new DukeException("Please enter a keyword.");
        }

        int id = 0;
        for(Task task: tasks){
            if(task.toString().contains(args)){
                boolean isFirstOne = id == 0;
                if (isFirstOne){
                    ui.printInfoHead("Here are the matching tasks in your list:");
                }
                id ++;
                ui.printOneTask(task, id);
            }
        }
        boolean isNotFound = id == 0;
        if(isNotFound){
            throw new DukeException("Not found this keyword.");
        }
        ui.printOutputTail();
    }
}
