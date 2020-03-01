package duke.command;

import java.time.LocalDate;

import duke.TaskList;
import duke.Ui;
import duke.excpetions.DukeException;

/**
 * A subtype of command which is used to manage the tasks already in the task list.
 */
public class ManageCommand extends Command{

    private int index;
    private String keywords;
    private LocalDate date;

    /**
     * A constructor that creates a manage command with type and index.
     *
     * @param type The type of a management command including : done ,list ,find and show.
     * @param index For "done" command, it refers to the index number of the task to be done.
     *              And the index of any other types of management command should be -1.
     * @param keywords It refers to the keyword the users want to search if the management command is type "find"
     *                 It should be null when the manage command is any other types.
     * @param date It refers to the date of the users wan to search for if the management command is type "show"
     *             It should be null when the manage command is any other types.
     */
    public ManageCommand(String type, int index, String keywords, LocalDate date){
        super(type);
        this.index = index;
        this.keywords = keywords;
        this.date = date;
    }

    /**
     * Execute a management command which do some operations on the current tasks.
     *
     * @param tasks The task list where the execution will be done.
     * @throws DukeException If some command which are not acceptable are sent to execute.
     */
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
        case "help":
            Ui.showHelpMessage();
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
