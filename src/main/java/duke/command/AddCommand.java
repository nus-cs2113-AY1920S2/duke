package duke.command;

import duke.TaskList;
import duke.excpetions.DukeException;

public class AddCommand extends Command {

    private String description;
    private String timeNotes;

    public AddCommand(String type,String description,String timeNotes) {
        super(type);
        this.description = description;
        this.timeNotes = timeNotes;
    }

    @Override
    public void execute(TaskList tasks) throws DukeException {
        switch(type){
        case "todo":
            tasks.addToDo(this);
            break;
        case "deadline":
            tasks.addDeadline(this);
            break;
        case "event":
            tasks.addEvent(this);
            break;
        default:
            throw new DukeException();
        }
    }

    public String getDescription() {
        return description;
    }

    public String getTimeNotes() {
        return timeNotes;
    }
}
