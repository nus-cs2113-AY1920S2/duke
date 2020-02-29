package duke.command;

import duke.TaskList;
import duke.excpetions.DukeException;

/**
 * A subtype of command which is used to add a new task to the task list.
 * Every add command includes description and time notes of a task.
 */
public class AddCommand extends Command {

    private String description;
    private String timeNotes;

    /**
     * A constructor creates a new add command using string of type, description and time notoes.
     *
     * @param type The types of the add commands can be "todo", "deadline" and "event".
     * @param description A description about the content of the task.
     * @param timeNotes The time notes is null when adding a todo task.
     *                  The time notes refers to deadline when adding a deadline task
     *                  and time period when adding an event.
     */
    public AddCommand(String type,String description,String timeNotes) {
        super(type);
        this.description = description;
        this.timeNotes = timeNotes;
    }

    /**
     *  This methods executes the add command that will add a respective type task to the type list.
     *
     * @param tasks The task list where the execution will be done.
     * @throws DukeException If there is anything wrong when trying to add a new task.
     */
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
