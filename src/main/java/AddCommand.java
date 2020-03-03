import java.text.MessageFormat;
import java.util.ArrayList;

public class AddCommand extends Command {

    protected String addType;
    protected String fullCommand;
    protected String editType;
    protected String additionalErrorMessage;

    public AddCommand (String command, String fullCommand) {
        this.addType = command;
        this.fullCommand = fullCommand;
        this.editType = "added";
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
            additionalErrorMessage = Messages.TODO_ERROR_MESSAGE;
        } else if (addType.equals("deadline")) {
            t = new Deadline(getAction(), getDate());
            additionalErrorMessage = String.format(Messages.DEADLINE_EVENT_ERROR_MESSAGE, "/by");
        } else {
            t = new Event(getAction(), getDate());
            additionalErrorMessage = String.format(Messages.DEADLINE_EVENT_ERROR_MESSAGE, "/at");
        }
        TaskList.addTask(t);
        return t;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t = addToList();
            ui.out.println(String.format(Messages.MESSAGE_ADD_DELETE_SUCCESS,
                    editType, t, tasks.getSize(), tasks.checkSingular()));
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