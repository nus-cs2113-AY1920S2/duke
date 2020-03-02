import java.text.MessageFormat;
import java.util.ArrayList;

public class AddCommand extends Command {
    protected String addType;
    protected String fullCommand;
    protected String additionalErrorMessage;
    public static final String TODO_ERROR_MESSAGE = ".";
    public static final String DEADLINE_EVENT_ERROR_MESSAGE =
            "and needs a date indicated by \"%1$s \".";


    public AddCommand (String command, String fullCommand) {
        super();
        this.addType = command;
        this.fullCommand = fullCommand;
    }

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

    public String getDate() throws IndexOutOfBoundsException{
        int indexOfDate;
        if (addType.equals("deadline")) {
            indexOfDate = fullCommand.indexOf("/by") + 4;
        } else {
            indexOfDate = fullCommand.indexOf("/at") + 4;
        }
        return fullCommand.substring(indexOfDate);
    }

    public Task addToList() {
        Task t;
        if (addType.equals("todo")) {
            t = new Todo(getAction());
            additionalErrorMessage = TODO_ERROR_MESSAGE;
        } else if (addType.equals("deadline")) {
            t = new Deadline(getAction(), getDate());
            additionalErrorMessage = String.format(DEADLINE_EVENT_ERROR_MESSAGE, "/by");
        } else {
            t = new Event(getAction(), getDate());
            additionalErrorMessage = String.format(DEADLINE_EVENT_ERROR_MESSAGE, "/at");
        }
        TaskList.addTask(t);
        return t;
    }

    @Override
    public void execute (TaskList tasks, Ui ui) {
        try {
            Task t = addToList();
            ui.out.println(String.format(Messages.MESSAGE_ADD_SUCCESS,
                    t, tasks.getSize(), tasks.checkSingular()));
        } catch (IndexOutOfBoundsException e) {
            ui.out.println(String.format(Messages.MESSAGE_INVALID_DESCRIPTION,
                    addType, additionalErrorMessage));
        }
    }

    @Override
    public boolean isExit(){
        return false;
    };


}

    /*public static void checkMissingDescription() throws DukeException {

        int index = 0;
        if (action.trim().isEmpty()) {
            throw new EmptyActionDescriptionException();
        }
        if (command.equals("deadline")) {
            index = taskDescription.indexOf("/by ");
            if (action.startsWith("/by")) {
                index = -1;
            }
        } else if (command.equals("event")) {
            index = taskDescription.indexOf("/at ");
            if (action.startsWith("/at")) {
                index = -1;
            }
        } else if (command.equals("done") || command.equals("delete")) {
            int listNumber = Integer.parseInt(action);
            if (listNumber > taskList.size()) {
                index = -1;
            }
        }
        if ((action.length() == 0) || (index == -1)) {
            throw new DukeException();
        }
    }*/
