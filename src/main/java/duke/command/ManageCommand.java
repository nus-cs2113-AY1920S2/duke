package duke.command;

import java.time.LocalDate;

import duke.TaskList;
import duke.excpetions.DukeException;
import duke.task.Task;

public class ManageCommand extends Command{

    private int index;
    private LocalDate date;

    public ManageCommand(String type,int index,LocalDate date){
        super(type);
        this.index = index;
        this.date = date;
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
        case "show":
            tasks.showOneDayTasks(this);
            break;
        default:
            throw new DukeException();
        }
    }

    public int getIndex() {
        return index;
    }

    public LocalDate getDate() {
        return date;
    }
}
