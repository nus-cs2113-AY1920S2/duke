package duke.command;

import duke.TaskList;
import duke.excpetions.DukeException;

public class ManageCommand extends Command{

    private int index;

    public ManageCommand(String type,int index){
        super(type);
        this.index = index;
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
        default:
            throw new DukeException();
        }
    }

    public int getIndex() {
        return index;
    }
}
