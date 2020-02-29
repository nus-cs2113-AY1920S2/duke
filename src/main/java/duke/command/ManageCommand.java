package duke.command;

import java.time.LocalDate;

import duke.TaskList;
import duke.excpetions.DukeException;

public class ManageCommand extends Command{

    private int index;
    private String keywords;
    private LocalDate date;

    public ManageCommand(String type, int index, String keywords, LocalDate date){
        super(type);
        this.index = index;
        this.keywords = keywords;
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
        case "find":
            tasks.searchTasks(this);
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

    public String getKeywords() {
        return keywords;
    }

    public LocalDate getDate() {
        return date;
    }
}
