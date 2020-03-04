package Duke.Command;

import Duke.UI.Ui;
import Duke.UI.Messages;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.TaskList;
import Duke.Storage;

/**
 * Handles adding different tasks such as Todo, Deadline and Event, to the list in the program.
 */
public class AddCommand extends Command {

    protected String addType;
    protected String fullCommand;
    protected String editType;

    /**
     * Constructor of AddCommand.
     *
     * @param command first word specified by user indicating type of Task to add to the list.
     * @param fullCommand entire input from user.
     */
    public AddCommand (String command, String fullCommand) {
        this.addType = command;
        this.fullCommand = fullCommand;
        this.editType = "added";
    }

    /**
     * Gets the action or task description specified by user.
     *
     * @return String containing task description.
     * @throws IndexOutOfBoundsException If user format is wrong due to unspecified action or date.
     */
    public String getAction() throws IndexOutOfBoundsException{
        String action;
        if (addType.equals("todo")) {
            action = fullCommand.substring(5);
        } else if (addType.equals("deadline")) {
            int indexOfDate = fullCommand.indexOf("/by");
            action = fullCommand.substring(9, indexOfDate);
        } else {
            int indexOfDate = fullCommand.indexOf("/at");
            action = fullCommand.substring(6, indexOfDate);
        }
        return action;
    }

    /**
     * Gets the date of deadline or event specified by user.
     *
     * @return String containing the date.
     * @throws IndexOutOfBoundsException If the user fails to include date correctly in input.
     */
    public String getDate() throws IndexOutOfBoundsException{
        int indexOfDate;
        if (addType.equals("deadline")) {
            indexOfDate = fullCommand.indexOf("/by") + 4;
        } else {
            indexOfDate = fullCommand.indexOf("/at") + 4;
        }
        return fullCommand.substring(indexOfDate);
    }

    /**
     * Determines which error message to send depending on type of task specified by user.
     *
     * @return Returns the error message to print.
     */
    public String getAdditionalErrorMessage () {
        if (addType.equals("todo")) {
            return Messages.TODO_ERROR_MESSAGE;
        } else if (addType.equals("deadline")) {
            return String.format(Messages.DEADLINE_EVENT_ERROR_MESSAGE, "/by");
        } else {
            return String.format(Messages.DEADLINE_EVENT_ERROR_MESSAGE, "/at");
        }
    }

    /**
     * Creates a new task object of the type(Todo, Deadline, Event) specified by user and adds it to the list.
     *
     * @return Returns the task created.
     */
    public Task addToList() {
        Task t;
        if (addType.equals("todo")) {
            t = new Todo(getAction());
        } else if (addType.equals("deadline")) {
            t = new Deadline(getAction(), getDate());
        } else {
            t = new Event(getAction(), getDate());
        }
        TaskList.addTask(t);
        return t;
    }

    /**
     * Executes the command.
     *
     * @param tasks The object class containing list of tasks in the program.
     * @param ui The object class handling printing output to the user.
     * @param storage The object class for saving program to file.
     */
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t = addToList();
            ui.out.println(String.format(Messages.MESSAGE_ADD_DELETE_SUCCESS,
                    editType, t, tasks.getSize(), tasks.checkSingular()));
        } catch (IndexOutOfBoundsException e) {
            ui.out.println(String.format(Messages.MESSAGE_INVALID_DESCRIPTION,
                    addType, getAdditionalErrorMessage()));
        }
    }

    /**
     * Indicates program not ready to exit.
     *
     * @return isExit() is false and program should continue running.
     */
    @Override
    public boolean isExit(){
        return false;
    };

}