package duke.command;

import duke.TaskList;
import duke.excpetions.DukeException;

public class ManageCommand extends Command{

    private int index;
    private String searchTerm;

    public ManageCommand(String type,int index,String searchTerm){
        super(type);
        this.index = index;
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(TaskList tasks) throws DukeException{
        switch(type){
        case "done":
            tasks.doneTask(this);
            break;
        case "list":
            tasks.listTasks();
            break;
        case "find":
            tasks.searchTasks(this);
            break;
        default:
            throw new DukeException();
        }
    }

    public int getIndex() {
        return index;
    }

    public String getSearchTerm() {
        return searchTerm;
    }
}
